<template>
  <div class="box">
    <el-card style="background:#fff;border:none;height:auto;width:100%;">
      <el-tabs   v-model="activeName" @tab-click="changeSort">
        <el-tab-pane v-for="(sort,index) in sorts" :key="index" :label="sort" :name="sort"></el-tab-pane>
      </el-tabs>
      </el-card>
      <el-empty :image-size="100" style="height:100px" v-if="!movies.length"></el-empty>
      <div class="toplist">
          <div v-for="(movie,index) in movies" :key="index" style="display: flex;flex-flow: row nowrap;width:200px">
            <el-link target="_blank">
            <span style="background:rgb(238, 238, 238);width:20px;height:20px;text-align: center;margin-right:5px">{{index}}</span>
            <span>{{movie.moviename}}</span>
            </el-link>
          </div>
      </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName:"剧情",
      movies:[],
      sorts:["动作","间谍","警匪","医学","武侠","古装","运动","战争","历史","灾难",'冒险',"西部","玄幻","奇幻","科幻","幻想","贺岁","犯罪",
            "黑帮","伦理","喜剧","爱情","恐怖","悬疑","青春","动画",'纪录',"戏曲","爱国","歌舞","记录"],
    }
  },
  mounted() {
    this.getMovieinfobytop("剧情")
  },
  methods: {
    changeSort(tab, event) {
       this.getMovieinfobytop(this.sorts[tab.index]) 
      },
      getMovieinfobytop(sort){
        this.$axios({
          url:"/movie/getMovieinfobytop",
          params:{
            sort:sort
          }
        }).then((res)=>{
          this.movies=res.data
        })
      }
  },
}
</script>
<style scoped>
.box {
  width: 80%;
  margin-left: 10%;
  background: #fff;
}
.toplist {
  /* width: 80%; */
  margin-left: 40px;
  background: #fff;
  height: 50vh;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-content: flex-start;
}
.toplist div {
  margin-right:30px;
  margin-bottom:60px
}
</style>