<template>
  <div class="intro">
    <div class="title">
      <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
      <span>剧情简介</span>
    </div>
    <div style="margin-bottom:40px">
        <p>{{intro}}</p>
      </div>
    <div class="titl">
      <div  style="display: flex; flex-flow: row nowrap;justify-content: flex-start;">
        <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
        <span>演员人员</span>
      </div>
      <div>
        <el-link @click.native="toPersons">全部<i class="el-icon-arrow-right"></i> </el-link>
      </div>
    </div>
    <div class="persons">
      <el-card v-for="(co,index) in 4" :key="index" body-style="padding:0px">
        <img src="https://p0.pipi.cn/basicdata/25bfd692dddf2ab535339e262555ae198aae0.jpg?imageView2/1/w/128/h/170" alt="">
        <div class="name">
          安特·沃德
        </div>
      </el-card>
    </div>
    <div class="titl">
      <div  style="display: flex; flex-flow: row nowrap;justify-content: flex-start;">
        <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
        <span>图集</span>
      </div>
      <div>
        <el-link @click.native="toImages">全部<i class="el-icon-arrow-right"></i> </el-link>
      </div>
    </div>
    <div class="imgs" style="margin-bottom:70px">
      <div class="bigimg">
        <img :src="resourcesPath+files[0]" alt="">
      </div>
      <div class="smallimg">
        <div>
          <span>
            <img :src="resourcesPath+files[1]" alt="">
          </span>
          <span>
            <img :src="resourcesPath+files[2]" alt="">
          </span>
        </div>
        <div>
          <span>
            <img :src="resourcesPath+files[3]" alt="">
          </span>
          <span>
            <img :src="resourcesPath+files[4]" alt="">
          </span>
        </div>
      </div>
    </div>
    <div class="titl">
      <div  style="display: flex; flex-flow: row nowrap;justify-content: flex-start;">
        <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
        <span>热门短评</span>
      </div>
      <div>
        <el-button round size="small">写短评</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      files:[],
      resourcesPath:this.$resourcesPath
    }
  },
  
  mounted() {
  },
  props:["intro","url"],
  watch:{
    url(newvalue,oldvalue){
      if (newvalue!=undefined) {
        this.getFlies(newvalue+"\\剧照")
      }
    }
  },
  methods: {
    getFlies(url){
      this.$axios({
        url:"/movie/getFlies",
        params:{
          path:url
        }
      }).then((res)=>{
        if (res.data.length) {
          if (res.data.length<5) {
            for (let i = 0; i < res.data.length; i++) {
                this.files.push(res.data[i])
            }
          }else{
            for (let i = 0; i < 5; i++) {
                this.files.push(res.data[i])
            }
          }
        }
      })
    },
    toImages(){
      this.$store.state.page.changepage("third");
    },
    toPersons(){
      this.$store.state.page.changepage("second");
    }
  },
}
</script>
<style scoped>
.intro {
  width: 100%;
  height: auto;
}
.title {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
  margin-bottom: 20px;
}
.titl {
  margin-bottom: 20px;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
}
.persons {
  width: 100%;
  height: auto;
  display: flex;
  margin-bottom: 40px;
  flex-flow: row nowrap;
  justify-content: flex-start;
}
.persons .el-card {
  width: 170px;
  height: 230px;
  margin-right: 20px;
  text-align: center;
}
.persons .el-card img{
  width: 100%;
  height: 200px;
}
.imgs {
  width: 100%;
  height: 250px;
  display: flex;
  flex-flow: row nowrap;
}
.imgs .bigimg {
  width: 55%;
  height: 250px;
}
.imgs .bigimg img {
  width: 100%;
  height: 100%;
}
.imgs .smallimg {
  display: flex;
  flex-flow: column;
  align-items: space-around;
}
.imgs .smallimg div span {
  margin-left: 10px;
}
.imgs  .smallimg  div span img{
  width: 150px;
  height: 122px;
}
</style>