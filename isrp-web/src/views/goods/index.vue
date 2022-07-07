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
              userstore.info.userId
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
        <el-upload list-type="picture-card" :auto-upload="false" :on-change="handleChange" :multiple="true">
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
        <el-input v-model="form.goodsName" placeholder="请输入您的商品名称..."/>
      </el-form-item>
      <el-form-item label="商品价格" :label-width="formLabelWidth">
        <el-input v-model="form.goodsPrice" placeholder="请输入您的商品价格..."/>
      </el-form-item>
      <el-form-item label="商品描述" :label-width="formLabelWidth">
        <el-input v-model="form.goodsDesc" placeholder="请输入您的商品描述..."/>
      </el-form-item>
      <el-form-item label="商品状态" :label-width="formLabelWidth">
        <el-input v-model="form.goodsStatus" placeholder="请输入0或1，0为待租中，1为出租中" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false,updateGoodsByGoodsId()"
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
      <el-form-item label="商品价格" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsPrice" />
      </el-form-item>
      <el-form-item label="商品描述" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsDesc" />
      </el-form-item>
      <el-form-item label="商品分类" :label-width="formLabelWidth">
        <el-input v-model="insertGoodsForm.goodsCategorySecondId" />
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
import { selectGoodsByGoodsName, insertGoods,updateGoods } from "@/api/goods";
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
const handleChange: UploadProps["onChange"] = (uploadFile, uploadFiles) => {
  let imageList: any = [];
  uploadFiles.forEach((element) => {
    imageList.push(element.raw);
  });
  insertGoodsForm.goodsImg = imageList;
  form.goodsImg = imageList;
};
const form = reactive({
  goodsName: "",
  goodsId: "",
  goodsPrice: "",
  goodsDesc: "",
  createTime: "",
  goodsImg: [],
  goodsSaleCount: 0,
  goodsStatus: 1,
  goodsCategorySecondId: 2,
  userId: userstore.info.userId,
  orderModelId: "1",
});
const updateGoodsByGoodsId = () =>{
  let formData: any = new FormData();
  formData.append("goodsId", form.goodsId);
  formData.append("goodsName", form.goodsName);
  formData.append("goodsPrice", form.goodsPrice);
  formData.append("goodsDesc", form.goodsDesc);
  formData.append("goodsStatus", form.goodsStatus);
  form.goodsImg.forEach((element) => {
    formData.append("goodsImg", element);
  });
  updateGoods(formData);
  ElMessage({
          type: "success",
          message: "修改成功,请刷新页面",
        });
}
const addGoods = () => {
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
  insertGoodsForm.goodsImg.forEach((element) => {
    formData.append("goodsImg", element);
  });
  insertGoods(formData);
  ElMessage({
          type: "success",
          message: "发布成功,请刷新页面",
        });
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
  goodsCategorySecondId: 2,
  userId: userstore.info.userId,
  orderModelId: "1",
});
const getGoodsInfo = (
  goodsId,
  goodsName,
  goodsImg,
  goodsPrice,
  goodsDesc,
  goodsStatus,
  userId
) => {
  form.goodsId = goodsId;
  form.goodsName = goodsName;
  form.goodsImg = goodsImg;
  form.goodsPrice = goodsPrice;
  form.goodsDesc = goodsDesc;
  form.goodsStatus = goodsStatus;
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
    goodsInfo.value.goodsImg = goodsInfo.value.goodsImg.split(",");

  });
};
onMounted(async () => {
  goodsInfo.value = await selectGoodsByUserId(userstore.info.userId).then(
    (res: any) => res.data
  );
  goodsInfo.value.forEach(element => {
      element.goodsImg = element.goodsImg.split(",");
  })
});
</script>