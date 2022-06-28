<template>
  <div>
    <el-form-item label="商品搜索" style="margin-left: 80px">
      <el-input
        placeholder="请输入你要搜索的商品名称..."
        clearable
        type="text"
        class="w-200"
      />
      <el-button
        type="success"
        :icon="Search"
        round
        class="w-25"
        style="margin-left: 20px"
        >搜索</el-button
      >
      <el-button type="primary" round class="w-25"
        ><el-icon><Upload /></el-icon>发布商品</el-button
      >
    </el-form-item>
  </div>
  <el-table :data="goodsInfo" style="width: 100%; text-align: center">
    <el-table-column fixed prop="goodsId" label="商品序号" width="150" />
    <el-table-column prop="goodsName" label="商品名称" width="120" />
    <el-table-column label="商品图片" width="170">
      <template #default="scope">
        <el-image :src="scope.row.goodsImg">
          <template #placeholder>
            <div class="image-slot">Loading<span class="dot">...</span></div>
          </template>
        </el-image>
      </template>
    </el-table-column>
    <el-table-column prop="goodsPrice" label="商品价格" width="120" />
    <el-table-column prop="goodsDesc" label="商品描述" width="200" />
    <el-table-column prop="goodsStatus" label="商品状态" width="120" />
    <el-table-column prop="goodsSaleCount" label="销量" width="120" />
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
          @click="deleteGoods(scope.row.goodsId)"
          >删除商品</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogFormVisible" title="修改商品">
    <el-form :model="form">
      <el-form-item label="商品图片" :label-width="formLabelWidth">
        <el-upload action="#" list-type="picture-card" :auto-upload="false">
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
        <el-input v-model="form.goodsName" />
      </el-form-item>
      <el-form-item label="商品价格" :label-width="formLabelWidth">
        <el-input v-model="form.goodsPrice" />
      </el-form-item>
      <el-form-item label="商品描述" :label-width="formLabelWidth">
        <el-input v-model="form.goodsDesc" />
      </el-form-item>
      <el-form-item label="商品状态" :label-width="formLabelWidth">
        <el-input v-model="form.goodsStatus" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false"
          >确定修改</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import { Search } from "@element-plus/icons-vue";
import { selectGoodsByUserId } from "@/api/goods";
import { userStore } from "@/store/user";
import type { UploadFile } from "element-plus";
import { deleteGoodsByGoodsId } from "@/api/goods";
import { ElMessage } from "element-plus";
const disabled = ref(false);
const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const formLabelWidth = "140px";
const dialogFormVisible = ref(false);
const userstore = userStore();
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
const deleteGoods = (goodsId) => {
  deleteGoodsByGoodsId(goodsId).then(() => {
    getAllGoodsFun();
    ElMessage({
      type: 'success',
      message: '删除成功'
    })

  });
};
const form = reactive({
  goodsName: "",
  goodsId: "",
  goodsPrice: "",
  goodsDesc: "",
  goodsStatus: "",
  goodsSaleCount: "",
  createTime: "",
  goodsImg: "",
});
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
  });
};
onMounted(async () => {
  goodsInfo.value = await selectGoodsByUserId(userstore.info.userId).then(
    (res: any) => res.data
  );
  console.log(goodsInfo.value);
});
</script>