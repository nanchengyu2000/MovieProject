<template>
  <div>
    <div class="title">
      <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
      <span>预告片</span>
    </div>
    <div class="advance">
      <el-card  v-for="(co,index) in 3" :key="index" body-style="padding:0px;width: 100%;height: 100px;display:flex;flex-flow: row nowrap;justify-content:flex-start;margin-bottom:10px">
        <div class="image">
          <img src="https://p0.pipi.cn/friday/fb1776b07b2f0c96284bbbbf99133072.jpg?imageView2/1/w/120/h/68" alt="">
        </div>
        <div class="info">
          <div class="title">
            <el-link type="danger">忍者神龟：崛起》《忍者神</el-link>
            </div>
          <div class="played">
            <span>
              <img style="width:15px;hight:15px" src="../assets/视频播放时播放.png" alt="">
            </span>
            94.4万</div>
        </div>
      </el-card>
    </div>
    <div class="titl">
      <div  style="display: flex; flex-flow: row nowrap;justify-content: flex-start;">
        <div style="width:5px;height:auto;background:rgb(239, 66, 56);margin-right:5px"></div>
        <span>相关电影</span>
      </div>
      <div>
        <el-link @click.native="toPersons">全部<i class="el-icon-arrow-right"></i> </el-link>
      </div>
    </div>
    <div class="correlation">
      <el-card v-for="sametype in sametypes"  :key="sametype.movieid" body-style="width:130px;height:200px;padding:0px;display:flex;flex-flow:column;align-items:center;" style="margin-right:10px;margin-bottom:10px">
        <div class="img" @click="getinfo(sametype.moviename)">
          <img style="width:100%;height:140px" :src="resourcesPath+sametype.imagepath" alt="">
        </div>
        <div class="title" style="height:10px">
          {{sametype.moviename}}
        </div>
        <div class="score" style="color:rgb(255, 180, 0);font-style:italic;">
          {{sametype.grade}}
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      sametypes:[],
      resourcesPath:this.$resourcesPath
    }
  },
  props:["movieinfo"],
  watch:{
    deep:true,
    movieinfo(newvalue,oldvalue){
      this.getMoviesbysort(newvalue.sort,newvalue.moviename)
    }
  },
  methods: {
    getinfo(moviename){
      this.$router.replace({
        path:"/Index/MoveInfo/",
        query:{
          moviename:moviename
        }
      })
      location.reload()
    },
    getMoviesbysort(sort,moviename){
      this.$axios({
        url:"/movie/getMoviesbysort",
        params:{
          sort:sort,
          moviename:moviename
        }
      }).then((res)=>{
        this.sametypes=res.data
      })
    },
  },
}
</script>
<style scoped>
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
  
  margin-top: 30px;
  
}
.advance {
  width: 100%;
  display: flex;
  flex-flow: column;
}
.advance .el-card .image {
  width: 150px;
  height: 100%;
}
.advance .el-card .image img {
  width: 150px;
  height: 100%;
}
.advance .el-card .info {
    width: 250px;
    height: 100%;
    display: flex;
    flex-flow: column;
    margin-left: 20px;
    justify-content: center;
    align-items: flex-start;
}
.advance .el-card .info .title:hover {
  cursor:pointer;
}
.correlation {
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
}
</style>