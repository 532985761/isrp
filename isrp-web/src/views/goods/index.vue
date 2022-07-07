<template>
  <div>
    <el-form-item label="商品搜索" style="margin-left: 80px">
      <el-input
        placeholder="请输入你要搜索的商品名称..."
        clearable
        type="text"
        class="w-200"
        v-model="keyword"
      />
      <el-button
        type="success"
        :icon="Search"
        round
        class="w-25"
        style="margin-left: 20px"
        @click="searchGoodsName(keyword)"
        >搜索</el-button
      >
      <el-button
        type="primary"
        round
        class="w-25"
        @click="insertFormVisible = true"
        ><el-icon><Upload /></el-icon>发布商品</el-button
      >
    </el-form-item>
  </div>
  <el-table
    :data="goodsInfo"
    style="width: 100%; text-align: center"
    height="595"
  >
    <el-table-column fixed prop="goodsId" label="商品序号" width="150" />
    <el-table-column prop="goodsName" label="商品名称" width="120" />
    <el-table-column label="商品图片" width="170">
      <template #default="scope">
        <el-carousel indicator-position="outside" height="100px">
          <el-carousel-item v-for="item in scope.row.goodsImg" :key="item">
            <el-image :src="item" />
          </el-carousel-item>
        </el-carousel>
      </template>
    </el-table-column>
    <el-table-column prop="goodsPrice" label="商品价格" width="120" />
    <el-table-column prop="goodsDesc" label="商品描述" width="200" />
    <el-table-column label="商品状态" width="120">
      <template #default="scope">
        <el-tag type="success" effect="dark" v-if="scope.row.goodsStatus == 1"
          >待租中</el-tag
        >
        <el-tag type="warning" effect="dark" v-if="scope.row.goodsStatus == 0"
          >出租中</el-tag
        >
        <el-tag type="info" effect="dark" v-if="scope.row.goodsStatus == 2"
          >售出</el-tag
        >
      </template>
    </el-table-column>
    <el-table-column prop="goodsSaleCount" label="销量" width="120">
      <template #default="scope">
        <el-tag type="info" effect="dark" v-if="scope.row.goodsSaleCount == 0"
          >无人购买</el-tag
        >
        <p v-if="scope.row.goodsSaleCount != 0">
          {{ scope.row.goodsSaleCount }}
        </p>
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="发布时间" width="175" />
    <el-table-column prop="rentLimitDays" label="最大租用时间" width="175" />
    <el-table-column prop="rentPricePerDay" label="每天租聘价格" width="175" />
    <el-table-column fixed="right" label="操作" width="200">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click="
            getGoodsInfo(
              scope.row.goodsId,
              scope.row.goodsName,
              scope.row.goodsImg,
              scope.row.goodsPrice,
              scope.row.goodsDesc,
              scope.row.goodsStatus,
              userstore.info.userId,
              scope.row.goodsCategorySecondId,
              scope.row.rentPricePerDay,
              scope.row.rentLimitDays
            ),
              (dialogFormVisible = true)
          "
          >修改商品</el-button
        >
        <el-button
          link
          type="warning"
          size="small"
          @click="open(scope.row.goodsId)"
          >删除商品</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogFormVisible" title="修改商品">
    <el-form :model="form">
      <el-form-item label="商品图片" :label-width="formLabelWidth">
        <el-upload
          list-type="picture-card"
          :auto-upload="false"
          :on-change="handleChange"
          :multiple="true"
        >
          <el-icon><Plus /></el-icon>
          <template #file="{ file }">
            <div>
              <img
                class="el-upload-list__item-thumbnail"
                :src="file.url"
                alt=""
              />
              <span class="el-upload-list__item-actions">
                <span
                  class="el-upload-list__item-preview"
                  @click="handlePictureCardPreview(file)"
                >
                  <el-icon><zoom-in /></el-icon>
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleRemove(file)"
                >
                  <el-icon><Delete /></el-icon>
                </span>
              </span>
            </div>
          </template>
        </el-upload>
        <el-dialog v-model="dialogVisible">
          <img w-full :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
      </el-form-item>
      <el-form-item label="商品名称" :label-width="formLabelWidth">
        <el-input
          v-model="form.goodsName"
          placeholder="请输入您的商品名称..."
        />
      </el-form-item>
      <el-form-item label="商品总价" :label-width="formLabelWidth">
        <el-input
          v-model="form.goodsPrice"
          placeholder="请输入您的商品价格..."
        />
      </el-form-item>
      <el-form-item label="商品描述" :label-width="formLabelWidth">
        <el-input
          v-model="form.goodsDesc"
          placeholder="请输入您的商品描述..."
        />
      </el-form-item>
      <el-form-item label="商品状态" :label-width="formLabelWidth">
        <el-input
          v-model="form.goodsStatus"
          placeholder="请输入0或1，0为待租中，1为出租中"
        />
      </el-form-item>
      <el-form-item label="商品分类" :label-width="formLabelWidth">
        <el-select
          v-model="form.goodsCategorySecondId"
          placeholder="请选择您商品的分类..."
        >
          <el-option label="手机" value="1" />
          <el-option label="对讲机" value="2" />
          <el-option label="耳机" value="3" />
          <el-option label="摄像机" value="4" />
          <el-option label="单反相机" value="5" />
          <el-option label="轿跑" value="6" />
          <el-option label="自行车" value="7" />
          <el-option label="小型汽车" value="8" />
          <el-option label="小型货车" value="9" />
          <el-option label="大型挂车" value="10" />
          <el-option label="挖掘机" value="11" />
          <el-option label="上海汤臣一品" value="12" />
          <el-option label="梦幻宅院" value="13" />
          <el-option label="林中小别墅" value="14" />
          <el-option label="跑步机" value="15" />
          <el-option label="台灯" value="16" />
        </el-select>
      </el-form-item>
      <el-form-item label="限制最大租用天数" :label-width="formLabelWidth">
        <el-input v-model="form.rentLimitDays" type="number" min="10" />
      </el-form-item>
      <el-form-item label="设置价格(价格/天)" :label-width="formLabelWidth">
        <el-input v-model="form.rentPricePerDay" type="number" min="1" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="(dialogFormVisible = false), updateGoodsByGoodsId()"
          >确定修改</el-button
        >
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="insertFormVisible" title="发布商品">
    <el-form :model="insertGoodsForm" enctype="multipart/form-data">
      <el-form-item label="商品图片" :label-width="formLabelWidth">
        <el-upload
          list-type="picture-card"
          :auto-upload="false"
          :on-change="handleChange"
          :multiple="true"
        >
          <el-icon><Plus /></el-icon>
          <template #file="{ file }">
            <div>
              <img
                class="el-upload-list__item-thumbnail"
                :src="file.url"
                alt=""
              />
              <span class="el-upload-list__item-actions">
                <span
                  class="el-upload-list__item-preview"
                  @click="handlePictureCardPreview(file)"
                >
                  <el-icon><zoom-in /></el-icon>
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleRemove(file)"
                >
                  <el-icon><Delete /></el-icon>
                </span>
              </span>
            </div>
          </template>
        </el-upload>
        <el-dialog v-model="dialogVisible">
          <img w-full :src="ImageUrl" alt="Preview Image" />
        </el-dialog>
      </el-form-item>
      <el-form-item label="商品名称" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsName" />
      </el-form-item>
      <el-form-item label="商品总价" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsPrice" />
      </el-form-item>
      <el-form-item label="商品描述" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsDesc" />
      </el-form-item>
      <el-form-item label="商品分类" :label-width="formLabelWidth">
        <el-select
          v-model="insertGoodsForm.goodsCategorySecondId"
          placeholder="请选择您商品的分类..."
        >
          <el-option label="手机" value="1" />
          <el-option label="对讲机" value="2" />
          <el-option label="耳机" value="3" />
          <el-option label="摄像机" value="4" />
          <el-option label="单反相机" value="5" />
          <el-option label="轿跑" value="6" />
          <el-option label="自行车" value="7" />
          <el-option label="小型汽车" value="8" />
          <el-option label="小型货车" value="9" />
          <el-option label="大型挂车" value="10" />
          <el-option label="挖掘机" value="11" />
          <el-option label="上海汤臣一品" value="12" />
          <el-option label="梦幻宅院" value="13" />
          <el-option label="林中小别墅" value="14" />
          <el-option label="跑步机" value="15" />
          <el-option label="台灯" value="16" />
        </el-select>
      </el-form-item>
      <el-form-item label="限制最大租用天数" :label-width="formLabelWidth">
        <el-input
          v-model="insertGoodsForm.rentLimitDays"
          type="number"
          min="10"
        />
      </el-form-item>
      <el-form-item label="设置价格(价格/天)" :label-width="formLabelWidth">
        <el-input
          v-model="insertGoodsForm.rentPricePerDay"
          type="number"
          min="1"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="insertFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="(insertFormVisible = false), addGoods()"
          >发布</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import { Search } from "@element-plus/icons-vue";
import { selectGoodsByUserId } from "@/api/goods";
import { selectGoodsByGoodsName, insertGoods, updateGoods } from "@/api/goods";
import { userStore } from "@/store/user";
import type { UploadFile } from "element-plus";
import { deleteGoodsByGoodsId } from "@/api/goods";
import { ElMessage, ElMessageBox, UploadProps } from "element-plus";
const disabled = ref(false);
const dialogImageUrl = ref("");
const ImageUrl = ref("");
const dialogVisible = ref(false);
const insertFormVisible = ref(false);
const formLabelWidth = "140px";
const dialogFormVisible = ref(false);
const userstore = userStore();
const keyword = ref("");
//图片push
const handleChange: UploadProps["onChange"] = (uploadFile, uploadFiles) => {
  let imageList: any = [];
  uploadFiles.forEach((element) => {
    imageList.push(element.raw);
  });
  insertGoodsForm.goodsImg = imageList;
  form.goodsImg = imageList;
};
//显示的form表单
const form = reactive({
  goodsName: "",
  goodsId: "",
  goodsPrice: "",
  goodsDesc: "",
  createTime: "",
  goodsImg: [],
  goodsSaleCount: 0,
  goodsStatus: 1,
  goodsCategorySecondId: null,
  userId: userstore.info.userId,
  orderModelId: "1",
  rentLimitDays: null,
  rentPricePerDay: null,
});
//修改商品方法
const updateGoodsByGoodsId = () => {
  if (form.goodsName == null || form.goodsName == "") {
    ElMessage({
      type: "warning",
      message: "请填写商品名称！",
    });
  } else if (form.goodsImg == null || form.goodsImg.length == 0) {
    ElMessage({
      type: "warning",
      message: "请上传商品图片",
    });
  } else if (form.goodsPrice == null || form.goodsPrice == "") {
    ElMessage({
      type: "warning",
      message: "请填写商品价格",
    });
  } else if (form.goodsDesc == null || form.goodsDesc == "") {
    ElMessage({
      type: "warning",
      message: "请填写商品描述",
    });
  } else if (form.goodsSaleCount == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品总价",
    });
  } else if (form.goodsCategorySecondId == null) {
    ElMessage({
      type: "warning",
      message: "请选择商品分类",
    });
  } else if (form.rentLimitDays == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品最大租用天数",
    });
  } else if (form.rentPricePerDay == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品每天租用价格",
    });
  } else {
    let formData: any = new FormData();
    formData.append("goodsId", form.goodsId);
    formData.append("goodsName", form.goodsName);
    formData.append("goodsPrice", form.goodsPrice);
    formData.append("goodsDesc", form.goodsDesc);
    formData.append("goodsStatus", form.goodsStatus);
    formData.append("rentLimitDays", form.rentLimitDays);
    formData.append("rentPricePerDay", form.rentPricePerDay);
    formData.append("goodsCategorySecondId", form.goodsCategorySecondId);
    form.goodsImg.forEach((element) => {
      formData.append("goodsImg", element);
    });
    updateGoods(formData).then(()=>{
       getAllGoodsFun();
    })
    ElMessage({
      type: "success",
      message: "修改成功",
    });
  }
};
//发布商品方法
const addGoods = () => {
  if (insertGoodsForm.goodsName == null || insertGoodsForm.goodsName == "") {
    ElMessage({
      type: "warning",
      message: "请填写商品名称！",
    });
  } else if (
    insertGoodsForm.goodsImg == null ||
    insertGoodsForm.goodsImg.length == 0
  ) {
    ElMessage({
      type: "warning",
      message: "请上传商品图片",
    });
  } else if (
    insertGoodsForm.goodsPrice == null ||
    insertGoodsForm.goodsPrice == ""
  ) {
    ElMessage({
      type: "warning",
      message: "请填写商品价格",
    });
  } else if (
    insertGoodsForm.goodsDesc == null ||
    insertGoodsForm.goodsDesc == ""
  ) {
    ElMessage({
      type: "warning",
      message: "请填写商品描述",
    });
  } else if (insertGoodsForm.goodsSaleCount == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品总价",
    });
  } else if (insertGoodsForm.goodsCategorySecondId == null) {
    ElMessage({
      type: "warning",
      message: "请选择商品分类",
    });
  } else if (insertGoodsForm.rentLimitDays == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品最大租用天数",
    });
  } else if (insertGoodsForm.rentPricePerDay == null) {
    ElMessage({
      type: "warning",
      message: "请填写商品每天租用价格",
    });
  } else {
    let formData: any = new FormData();
    formData.append("userId", insertGoodsForm.userId);
    formData.append("goodsName", insertGoodsForm.goodsName);
    formData.append("goodsPrice", insertGoodsForm.goodsPrice);
    formData.append("goodsDesc", insertGoodsForm.goodsDesc);
    formData.append("goodsSaleCount", insertGoodsForm.goodsSaleCount);
    formData.append("goodsStatus", insertGoodsForm.goodsStatus);
    formData.append(
      "goodsCategorySecondId",
      insertGoodsForm.goodsCategorySecondId
    );
    formData.append("goodsSaleCount", insertGoodsForm.goodsSaleCount);
    formData.append("orderModelId", insertGoodsForm.orderModelId);
    formData.append("rentLimitDays", insertGoodsForm.rentLimitDays);
    formData.append("rentPricePerDay", insertGoodsForm.rentPricePerDay);
    insertGoodsForm.goodsImg.forEach((element) => {
      formData.append("goodsImg", element);
    });
    insertGoods(formData).then(()=>{
       getAllGoodsFun();
    })
    ElMessage({
      type: "success",
      message: "发布成功",
    });
  }
};
const searchGoodsName = (keyword) => {
  if (keyword == "" || keyword == undefined || keyword == null) {
    selectGoodsByUserId(userstore.info.userId).then((response: any) => {
      goodsInfo.value = response.data;
    });
  } else {
    selectGoodsByGoodsName(keyword).then((res: any) => {
      if (res.data.length == 0 || res.data == null) {
        ElMessage({
          type: "warning",
          message: "抱歉没有该商品",
        });
      } else {
        ElMessage({
          type: "success",
          message: "查询成功",
        });
        goodsInfo.value = res.data;
      }
    });
  }
};

const insertGoodsForm = reactive({
  goodsName: "",
  goodsPrice: "",
  goodsDesc: "",
  goodsImg: [],
  goodsSaleCount: 0,
  goodsStatus: 1,
  goodsCategorySecondId: null,
  userId: userstore.info.userId,
  orderModelId: "1",
  rentLimitDays: null,
  rentPricePerDay: null,
});
const getGoodsInfo = (
  goodsId,
  goodsName,
  goodsImg,
  goodsPrice,
  goodsDesc,
  goodsStatus,
  userId,
  goodsCategorySecondId,
  rentLimitDays,
  rentPricePerDay
) => {
  form.goodsId = goodsId;
  form.goodsName = goodsName;
  form.goodsImg = goodsImg;
  form.goodsPrice = goodsPrice;
  form.goodsDesc = goodsDesc;
  form.goodsStatus = goodsStatus;
  form.goodsCategorySecondId = goodsCategorySecondId;
  form.rentPricePerDay = rentPricePerDay;
  form.rentLimitDays = rentLimitDays;
};
const open = (goodsId) => {
  ElMessageBox.confirm("确认删除此商品吗？", "删除商品", {
    confirmButtonText: "删除",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    deleteGoodsByGoodsId(goodsId).then(() => {
      getAllGoodsFun();
    });
    ElMessage({
      type: "success",
      message: "删除成功！",
    });
  });
};
const handlePictureCardPreview = (file: UploadFile) => {
  dialogImageUrl.value = file.url!;
  dialogVisible.value = true;
};
const handleRemove = (file: UploadFile) => {
  console.log(file);
};
let goodsInfo: any = ref([]);
const getAllGoodsFun = () => {
  selectGoodsByUserId(userstore.info.userId).then((res: any) => {
    goodsInfo.value = res.data;
    // goodsInfo.value.goodsImg = goodsInfo.value.goodsImg.split(",");
    goodsInfo.value.forEach((element) => {
    element.goodsImg = element.goodsImg.split(",");
  });
  });
};
onMounted(async () => {
  goodsInfo.value = await selectGoodsByUserId(userstore.info.userId).then(
    (res: any) => res.data
  );
  goodsInfo.value.forEach((element) => {
    element.goodsImg = element.goodsImg.split(",");
  });
});
</script>