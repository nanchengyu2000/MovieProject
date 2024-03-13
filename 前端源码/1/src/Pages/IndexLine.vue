<template>
  <div class="box">
    <el-carousel :interval="5000" arrow="always">
    <el-carousel-item v-for="(carousel,index) in carousels" :key="index">
      <img @click="getinfo(carousel.moviename)" style="width:100%;height:100%" :src="resourcesPath+carousel.imagepath" alt="">
    </el-carousel-item>
  </el-carousel>
    <div style="width:100%;display:flex;flex-flow:row nowrap">
      <el-card style="background:#CCCCCC;border-radius: 10px;color:#FFFF;border:none;height:auto;width:100%;margin-top:50px;margin-bottom:20px;">
      <el-tabs   v-model="activeName" @tab-click="changeSort">
      <!-- <span    :class="sort.select?'sortlist select':'sortlist'" > -->
        <el-tab-pane v-for="(sort,index) in sorts" :key="index" :label="sort" :name="sort"></el-tab-pane>
      </el-tabs>
      </el-card>
    </div>
    <el-card style="background:rgb(37, 37, 37);height:50px;color:#FFFF;border:none">
      推荐电影
    </el-card>
    <el-empty :image-size="200" v-if="!movies.length"></el-empty>
    <div class="move_list">
      <el-card  v-for="(movie,index) in movies" :key="index" @click.native="getinfo(movie.moviename)" shadow="hover">
        <div class="image">
          <img :src="resourcesPath+movie.imagepath" alt="">
        </div>
        <div class="name">
          {{movie.moviename}}
        </div>
        <div class="sort">
          类型：{{movie.sort}}
        </div>
        <div class="score">
          <span style="width:60px">评分：</span>
          <el-rate
            v-model="movie.grade"
            disabled
            show-score
            text-color="#ff9900"
            score-template="{value}">
          </el-rate>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {Message} from 'element-ui'
export default {
  data() {
    return {
      activeName:"全部",
      sorts:["全部","动作","间谍","警匪","医学","武侠","古装","运动","战争","历史","灾难",'冒险',"西部","玄幻","奇幻","科幻","幻想","贺岁","犯罪",
            "黑帮","伦理","喜剧","爱情","恐怖","悬疑","青春","动画",'纪录',"戏曲","爱国","歌舞","记录"],
      score:3.7,
      userinfo:{},
      movies:[],
      carousels:[],
      resourcesPath:this.$resourcesPath
    }
  },
  mounted() {

    // if (this.$route.query.success) {
    //   Message.success({
    //     message:"登录成功！",
    //     type:"success"
    //   })
      this.getuserinfo(this.$route.query.username)
      // this.$router.push({query:{}})
    // }
    this.getmovieinfo()
  },
  methods: {
    getinfo(moviename){
      this.$router.push({
        path:"/Index/MoveInfo",
        query:{
          moviename:moviename
        }
      })
      // location.reload()
    },
    getmovieinfo(){
      this.$axios({
        url:"/movie/getAllMovies",
      }).then((res)=>{
        this.movies=res.data
        if (res.data.length>5) {
            for (let i = 0; i < 5; i++) {
            this.carousels.push(res.data[i])
          }
          }else {
            for (let j = 0; j < res.data.length; j++) {
            this.carousels.push(res.data[j])
          }
          }
      })
    },
    getuserinfo(username){
      this.$axios({
        url:"/member/getUser",
        params:{
          name:username
        }
      }).then((res)=>{
          this.userinfo=res.data
          localStorage.setItem("userinfo",JSON.stringify(res.data))
      })
    },
    changeSort(tab, event) {
      if (tab.index==0) {  //全部
        this.getmovieinfo()
      }else{
        this.$axios({
          url:"/movie/getbysort",
          params:{
            sort:this.sorts[tab.index]
          }
        }).then((res)=>{
          this.movies=res.data
          // location.reload()
        })
      }
    }
  },
}
</script>

<style scoped>
.box {
  width: 80%;
  margin-left: 10%;
  
}
.el-carousel{
  border-radius:10px;
  overflow: hidden;
}
.move_list {
  width: 100%;
  height: auto;
  margin-top: 20px;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
}
.move_list .el-card { 
  width: 250px;
  height: 280px;
  margin-top: 10px;
  margin-right: 30px;
  display: flex;
  flex-flow: column;
  color: #FFF;
  border: none;
  background: rgb(26, 26, 26);
}
.move_list .el-card .image {
  width: 100%;
  height: 180px;
}
.move_list .el-card .image img {
  width: 100%;
  height: 100%;
}
.score {
  width: 100%;
  display: flex;
  flex-flow: row nowrap;
}
.select{
  border-bottom: 2px solid rgb(180, 80, 98);
}
.sortlist:hover {
    cursor: default;
}
</style>
