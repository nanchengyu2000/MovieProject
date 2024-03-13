<template>
  <div class="box">
    <div class="header">
    <Search/>
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item v-for="(s,index) in 4" :key="index">{{index}}</van-swipe-item>
    </van-swipe>
  </div>
  <van-tabs v-model="activeName" @change="getbysort">
      <van-tab v-for="(sort,index) in sorts" :key="index"   :title="sort" :name="sort">
        <div class="movies">
          <div class="hot">
            <div class="hot-title">
              <span>热门推荐</span>
              <span class="info"><van-icon name="volume-o" />新剧即将上线</span>
            </div>
            <div class="hot-list">
              <div v-for="(movie,index) in movies"  @click="toplay(movie.moviename)" :key="index"  class="movie-info">
                <div class="movie-image">
                  <img :src="resource+movie.imagepath" alt="">
                </div>
                <div class="movie-name">
                  {{movie.moviename}}
                </div>
              </div>
            </div>
          </div>
          <div class="movie-all">
            <div class="repload">
              <img src="https://img2.baidu.com/it/u=2379235367,3089334830&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1671296400&t=527c6e64401cdc55413a8b4ea0024d31" alt="">
            </div>
            <div class="datainfo">
              <span class="dataimg">
                <img src="https://img2.baidu.com/it/u=2379235367,3089334830&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1671296400&t=527c6e64401cdc55413a8b4ea0024d31" alt="">
              </span>
              <div class="info">
                <span>斗罗大陆</span>
                <span>东方玄幻</span>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>
<script>
import Search from '../components/Search'
export default {
  components:{
    Search
  },
  data() {
    return {
      activeName: '全部',
      sorts:["全部","动作","间谍","警匪","医学","武侠","古装","运动","战争","历史","灾难",'冒险',"西部","玄幻","奇幻","科幻","幻想","贺岁","犯罪",
            "黑帮","伦理","喜剧","爱情","恐怖","悬疑","青春","动画",'纪录',"戏曲","爱国","歌舞","记录"],
      movies:[],
      resource:this.$resource
    }
  },
  mounted() {
    this.getAllMovies()
  },
  methods: {
    getAllMovies(){
      this.$axios({
        url:"/movie/getAllMovies",
      }).then((res)=>{
        this.movies=res.data
      })
    },
    getbysort(name,sort){
      if (sort!="全部") {
        this.$axios({
        url:"/movie/getbysort",
        params:{
          sort:sort
        }
      }).then((res)=>{
        this.movies=res.data
      })
      }else {
        this.getAllMovies()
      }
    },
    toplay(moviename){
      this.$router.push({
        path:"/Player",
        query:{
          moviename:moviename
        }
      })
    }
  },
}
</script>
<style scoped>
.header {
  width: 100%;
  height: auto;
  background-image: linear-gradient(to right, #d7d2cc 0%, #304352 100%);
  border-bottom-left-radius: 1rem;
  border-bottom-right-radius: 1rem;
}
.van-swipe {
  width: 80%;
  margin-top: 0.5rem;
  margin-left: 10%;
  border-radius: 1rem;
}
 .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  .van-tabs {
    width: 100%;
  }
  .movies {
    width: 80%;
    margin-left: 10%;
    margin-top: 0.5rem;
  }
  .hot-title {
    width: 100%;
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
  }
  .hot-title .info {
    width: 8rem;
    height: 1.4rem;
    text-align: center;
    background: #ccc;
    border-radius:0.7rem ;
  }
  .hot-list {
    width: 100%;
    margin-top: 1rem;
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
  }
  .hot-list .movie-info {
    width: 45%;
    display: flex;
    flex-flow: column;
    margin-bottom: 1rem;
  }
  .hot-list .movie-info .movie-image {
    width: 100%;
    height: 6rem;
    border-radius: 0.5rem;
    overflow: hidden;
  }
  .hot-list .movie-info .movie-image img {
    width: 100%;
    height: 100%;
  }
  .movie-all {
    width: 100%;
    display: flex;
    flex-flow: column;
  }
  .movie-all .repload {
    width: 100%;
    border-top-left-radius: 0.5rem;
    border-top-right-radius: 0.5rem;
    overflow: hidden;
  }
  .movie-all .repload img {
    width: 100%;
  }
  .movie-all .datainfo {
    width: 100%;
    display:flex;
    flex-flow: row nowrap;
  }
  .movie-all .datainfo .dataimg {
    width: 3rem;
    height: 3rem;
  }
  .movie-all .datainfo .dataimg img {
    width: 100%;
    height: 100%;
  }
  .movie-all .datainfo .info {
    display: flex;
    flex-flow:column;
  }
</style>