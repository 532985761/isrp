<template>
  <div>
    <h3 class="text-3xl text-shadow-md">
      欢迎来到厂家管理中心界面！
    </h3>
  </div>
  <el-divider />
  <div>
    <p class="text-xl text-shadow-md">平台商品展示</p>
  </div>
  
  <el-space wrap style="margin-left:110px;margin-top: 35px;">
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>共享单车</span>
          <p style="color:red;margin-left: 65px;">2元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div   class="text item">
        <img src="../../assets/die/bike.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>共享单车(升级版)</span>
          <p style="color:red;margin-left: 65px;">2.5元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/bike2.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>沃尔沃</span>
          <p style="color:red;margin-left: 65px;">20元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/car.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>兰博基尼</span>
          <p style="color:red;margin-left: 65px;">49.9元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/car2.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>小刀充电宝</span>
          <p style="color:red;margin-left: 65px;">2元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/god.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>报废电脑</span>
          <p style="color:red;margin-left: 65px;">5.5元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/macbook.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>抱抱龙玩具</span>
          <p style="color:red;margin-left: 65px;">0.5元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/dragon.jpg" alt="">
      </div>
    </el-card>
    <el-card  class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>猛禽汽车</span>
          <p style="color:red;margin-left: 65px;">52元/小时</p>
          <el-button class="button" text type="primary" style="margin-left:136px">商品详情</el-button>
        </div>
      </template>
      <div  class="text item">
        <img src="../../assets/die/car3.jpg" alt="">
      </div>
    </el-card>
  </el-space>
  
</template>
<script lang="ts" setup>

</script>
