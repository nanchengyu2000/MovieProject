<template>
  <el-container>
  <el-header>
    <div @click="toIndex" style="display: flex;flex-flow: row nowrap;justify-content: flex-start;align-items: center;">
      <img  style="height:50px;margin-top:15px" src="../assets/hAJAYnsfTQRzfj6Sxdb7fyNydxcjztMN_rg_small.png" alt="">
      <span style="font-size:20px;font-weight:600;margin-top:10px">电影推荐系统</span>
    </div>
    <span>
      <input placeholder="请输入电影名称" @keydown.enter="Search" v-model="search" class="input-with-select"/>
      <el-button @click="Search" style="background-color: rgb(88, 50, 48);border: none;" slot="append" icon="el-icon-search"></el-button>
    </span>
    <div style="display:flex">
    <div>
      <el-tooltip class="item" effect="dark" content="Top排行榜" placement="top-start">
      <li @click="toTopplan" class="el-icon-s-data" style="font-size:25px;margin-top:20px;margin-right:30px;color:red"></li>
      </el-tooltip>
    </div>
    <div>
      <el-popover
        placement="bottom"
        width="250"
        trigger="hover"
        v-model="visible">
          <ul style="list-style:none;display: flex;flex-flow: column;">
            <li  v-for="history in historys" :key="history.movieid" @click="Watch(history)" style="display: flex;flex-flow: row nowrap;justify-content: flex-start;margin-bottom:10px;">
              <span style="height:50px;width:30%;overflow: hidden;">
                <img style="width:100%;height:100%" :src="resourcesPath+history.imagepath"  alt="">
              </span>
              <span style="display: flex;flex-flow: column;justify-content: space-between;margin-left:10px">
                <span> <el-link type="danger">{{history.moviename}}</el-link></span>
                <span>
                  <el-rate 
                  v-model="history.grade"
                  show-score
                  disabled
                  text-color="#ff9900"
                  score-template="{value}"></el-rate>
                </span>
                <span>继续观看正片</span>
              </span>
            </li>
          </ul>
      <i slot="reference" class="el-icon-time" style="font-size:25px;margin-top:20px"></i>
      </el-popover>
    </div>
    <el-link v-show="flag"  type="primary">登录/注册</el-link>
    <span v-show="!flag" @mouseenter="isshow=true" @mouseleave="isshow=false"  class="image" style="margin-top:10px;width:200px">
       <!--http://localhost:8084/Movie/  -->
       <!--http://vyke0kcd.beesnat.com/Movie/  -->
       <el-avatar size="large" fit="cover" :src="'http://vyke0kcd.beesnat.com/Movie/'+userinfo.img"></el-avatar>
       <el-card body-style="padding:0px" v-show="isshow" class="info">
          <div class="userinfo" style="margin-top:20px">
            用户名:{{userinfo.usename}}
          </div>
          <ul style="height:100%;list-style:none">
            <li>个人中心</li>
            <li @click="quit">退出登录</li>
          </ul>
       </el-card>
    </span>
    </div>
  </el-header>
  <el-main>
    <router-view></router-view>
  </el-main>
</el-container>
</template>
<script>
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
export default {
  data() {
    return {
      flag:false,
      isshow:false,
      search:"",
      userinfo:{},
      visible: false,
      historys:[],
      resourcesPath:this.$resourcesPath
    }
  },
  mounted() {
    NProgress.start()
    this.userinfo=JSON.parse(localStorage.getItem("userinfo"))
    this.getAllhistory()
    NProgress.done()
  },
  methods: {
    getuserinfo(value){
      this.userinfo=value
    },
    getAllhistory(){
      this.$axios({
        url:"/movie/getAllhistory",
        params:{
          userid:this.userinfo.userid
        }
      }).then((res)=>{
        if (res.data) {
          for (let i = 0; i < res.data.length; i++) {
            this.gethistorymovie(res.data[i].movieid)
          }
        }
      })
    },
     gethistorymovie(movieid){
      this.$axios({
        url:"/movie/getMovieInfobyid",
        params:{
          movieid:movieid
        }
      }).then((res)=>{
        if (res.data) {
          this.historys.push(res.data)
        }
      })
    },
    quit(){
      localStorage.removeItem("userinfo")
      this.$router.replace("/")
    },
    toIndex(){
      this.$router.replace("/Index/IndexLine")
    },
    Watch(history){
      this.$router.push({
        path:"/Index/Player",
        query:{
          moviename:history.moviename,
          uploadpath:history.uploadpath,
          movieid:history.movieid
        }
      })
    },
    toTopplan(){
      this.$router.push("/Index/TopLine")
    },
    Search(){
      ///
      this.$router.push({
        path:"/Index/SeachResult",
        query:{
          search:this.search
        }
      })
    }
  },

}
</script>
<style scoped>
.el-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-flow:column;
  
}
ul li:hover {
  cursor: pointer;
}
.select {
  background:rgb(242, 89, 71)
}
.el-header {
  background-color: rgb(88, 50, 48);
  color: #fff;
  text-align: center;
  width: 100%;
  height: 100px;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-around;
  
}
.input-with-select {
  width:200px;
  height:38px;
  margin-top:15px;
  border: none;
  outline: none;
  color: #fff;
  background-color: rgb(88, 50, 48);
}
.image:hover>.el-avatar {
  position: absolute;
  transform: scale(1.2);
  z-index: 101;
}
.image .info {
  width:150px;
  height:auto;
  position: relative;
  z-index: 100;
  top: 25px;
  left: 50px;
}
.el-main {
  height: 90vh;
  width: 100%;
  background: rgb(21, 21, 21);
  color: #333;
}
.el-card ul {
  margin-top: 25px;
}
.el-card ul li {
  width: 100%;
  height: 50px;
  line-height: 50px;
}
.el-card ul li:hover {
  background: rgb(240, 240, 245);
  cursor: pointer;
}

</style>