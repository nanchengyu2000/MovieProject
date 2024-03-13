<template>
  <el-container style="height: 100%">
    <el-aside  width="auto">
        <Aside></Aside>
    </el-aside>
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <Dialog />
    </el-container>
  </el-container>
</template>

<script>
import Aside from "../AdmiComponents/Aside.vue";
import Header from "../AdmiComponents/Header.vue";
import Dialog from "../AdmiComponents/Dialog.vue";

export default {
  components: {
    Aside,
    Header,
    Dialog,
  },
  data() {
    return {
    };
  },
  methods: {
  },
  computed: {
    background() {
    },
  },
  mounted() {
  },
};
</script>

<style scoped>
.el-header{
    color: #333;
    background: #B45062;
  }
  
  .el-aside {
    background:#D0A982;
    color: #333;
    text-align: center;
  }
  
  .el-main {
    background-color: #F3C2CC;
    color: #333;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
