<template>
  <div>
    <el-empty :image-size="200" v-if="!movies.length"></el-empty>
    <div class="move_list">
      <el-card  v-for="(movie,index) in movies" :key="index" @click.native="getinfo(movie.moviename)" shadow="hover">
        <div class="image">
          <img :src="resourcesPath+movie.imagepath" alt="">
        </div>
        <div>
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
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      movies:[],
      resourcesPath:this.$resourcesPath
    }
  },
    mounted() {
      let search=this.$route.query.search
      this.Search(search)
    },
    methods: {
      Search(search){
        this.$axios({
          url:"/movie/Search",
          params:{
            search:search
          }
        }).then((res)=>{
            if (res.data) {
              this.movies=res.data
            }
        })
      }
    },
}
</script>
<style scoped>
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
</style>