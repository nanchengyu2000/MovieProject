<template>
    <el-menu
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    :collapse="$store.state.path.isCollapse"
    active-text-color="#FFFFF"
    :router="false"
    >
  <h1>{{text}}</h1> 
    <el-menu-item
      :index="noChildren.path"
      v-for="noChildren in noChildrens"
      :key="noChildren.path"
      @click="Addnames(noChildren,noChildren.path)"
    >
      <i :class="'el-icon-' + noChildren.icon"></i>
      <span slot="title">{{ noChildren.label }}</span>
    </el-menu-item>
    <el-submenu  v-for="hasChildren in hasChildrens" :key="hasChildren.path" :index="hasChildren.path">
      <template slot="title">
        <i :class="'el-icon-'+hasChildren.icon"></i>
        <span slot="title">{{hasChildren.label}}</span>
      </template>
      <el-menu-item-group v-for="children in hasChildren.children" :key="children.path">
        <el-menu-item @click="Addnames(children,children.path)" :index="children.path">{{children.label}}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      menu: [
        {
          path: "/IndexAdmi/userHome",
          name: "管理中心",
          label: "管理中心",
          icon: "s-home",
        },{
          path: "/IndexAdmi/UploadMovie/FolderList",
          name: "上传视频",
          label: "上传视频",
          icon: "edit",
        },{
          path: "/IndexAdmi/showbook",
          name: "视频管理",
          label: "视频管理",
          icon: "s-custom",
        },     
      ],
    };
  },
  computed: {
    hasChildrens() {
      return this.menu.filter((item) => item.children);
    },
    noChildrens() {
      return this.menu.filter((item) => !item.children);
    },
    text(){
      return this.$store.state.path.isCollapse? "系统" : "快看影院后台系统"
    },
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    Addnames(children,path){
      const obj={
        path:children.path,
        name:children.name,
        label:children.label
      }
      this.$router.replace({
        path:path
      })
    }
  },
};
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 150px;
  min-height: 400px;
}
.el-menu {
  height: 100%;
  border: none;
  background:#B45062
}
.el-menu h1 {
  height: 60px;
  text-align: center;
  line-height: 60px;
}
</style>