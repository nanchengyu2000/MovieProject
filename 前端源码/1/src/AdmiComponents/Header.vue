<template>
  <header>
    <div>
      <span class="header">
        <el-button
          @click="change"
          style="height: 50%"
          icon="el-icon-menu"
          size="mini"
        ></el-button>
        <el-breadcrumb
          separator-class="el-icon-arrow-right"
          style="margin-left: 10px"
        >
          <el-breadcrumb-item
            style="color: #fff"
            v-for="listname in listnames"
            :key="listname.path"
            :to="{ path: listname.path}"
            :replace="true"
            >{{ listname.label }}</el-breadcrumb-item
          >
        </el-breadcrumb>
      </span>
      <el-dropdown trigger="click" size="mini">
        <div class="Useimg">
          <img v-show="imgorvideo"  :src="resourcesPath+administratorinfo.img"/>
          <video v-show="!imgorvideo"  loop preload :src="resourcesPath+administratorinfo.img" muted  autoplay></video>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="showdialog">设置</el-dropdown-item>
          <el-dropdown-item @click.native="exit">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      administratorinfo:{},
      imgorvideo:true,
      resourcesPath:this.$resourcesPath
    };
  },
  mounted(){
    // this.$axios({
    //   url:"/User/getUser",
    //   params:{
    //     name:sessionStorage.getItem("usename")
    //   }
    // }).then((res)=>{
    //   this.userinfo=res.data
    //   this.selectContext(res.data.header)
    // })
    let administratorinfo=JSON.parse(sessionStorage.getItem("administratorinfo"))
    this.selectContext(administratorinfo.img)
    this.administratorinfo=administratorinfo
 },
  methods: {
    selectContext(value){
      var suffix = value.substr(value.lastIndexOf(".")+1);
      if (suffix=="mp4") {
        this.imgorvideo=false
      }else {
        this.imgorvideo=true
      }
    },
    change() {
      this.$store.commit("Change");  //对侧边栏的压缩
    },
    exit(){
      this.$router.replace({
        // path:'/BlogHome',      //离开系统
        path:'/'
      })
    },
    showdialog(){
      this.$store.commit("show")
    }
  },
  computed: {
    listnames(){
      return this.$store.state.path.listusernames
    }
  },
};
</script>

<style scoped>
.el-icon-arrow-down {
  font-size: 12px;
}
.el-dropdown {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  overflow: hidden;
  line-height: 50px;
}
.Useimg {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: black;
}
.Useimg img {
  width: 100%;
  height: 100%;
}
.Useimg video {
  width: 100%;
  height: 100%;
}
header div {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
header div .header {
  display: flex;
  flex-flow: row;
  justify-content: space-between;
  align-items: center;
}
.el-breadcrumb ::v-deep .el-breadcrumb__inner {
  color: #fff !important;
}
</style>