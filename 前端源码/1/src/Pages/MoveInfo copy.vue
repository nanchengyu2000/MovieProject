<template>
  <div class="big-box">
    <div class="header">
      <div class="image">
        <img :src="'http://localhost:8082/Movie/'+movieinfo.imagepath">
      </div>
      <div class="info">
        <div class="datainfo">
          <div class="name" style="font-size:30px;font-weight:700;margin-bottom:20px">{{movieinfo.moviename}}</div>
          <div class="sort" style="margin-bottom:10px">
            <span>{{movieinfo.sort}}</span>
          </div>
          <div class="contry" style="margin-bottom:10px">
              <span>{{movieinfo.moviearea}}</span>
              <span>{{movieinfo.duration}}</span>
          </div>
          <div class="time">
            {{movieinfo.dateissued}}中国大陆上映
          </div>
        </div>
        <div class="watch" style="margin-bottom:20px">
          <el-button @click="player(movieinfo)" style="background:rgb(117, 97, 137);color:rgb(255,255,255)">在线观看</el-button>
        </div>
      </div>
      <div class="score">
        <div style="font-size:16px;font-weight:600">影视评分</div>
        <div class="score-show">
          <span class="grade" style="font-size:40px;margin-right:10px;color:rgb(255, 198, 0)">{{score}}</span>
          <span style="margin-top:5px">
            <el-rate 
              v-model="score"
              disabled
              :colors="colors">
            </el-rate>
            100人评分
          </span>
        </div>
      </div>
    </div>
    <div class="main">
      <div class="intr">
      <el-tabs v-model="activeName"  @tab-click="handleClick">
        <el-tab-pane label="简介" name="first">
          <Intro :intro="movieinfo.intro" :url="movieinfo.uploadpath"/>
        </el-tab-pane>
        <el-tab-pane label="演员人员" name="second">
          <Persons :url="movieinfo.uploadpath"/>
        </el-tab-pane>
        <el-tab-pane label="图集" name="third">
          <Images :url="movieinfo.uploadpath"/>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="otherinfo">
      <el-tabs v-model="activeName1"  @tab-click="handleClick">
        <el-tab-pane label="预告片" name="first">
          <Advance :url="movieinfo.uploadpath"/>
        </el-tab-pane>
        <el-tab-pane label="相关资讯" name="second">
          相关资讯
        </el-tab-pane>
      </el-tabs>
    </div>
    </div>
  </div>
</template>
<script>
import Images from '../components/Images.vue'
import Intro from '../components/Intro.vue'
import Persons from '../components/Persons.vue'
import Advance from '../components/Advance.vue'
export default {
  components:{
    Images,
    Intro,
    Persons,
    Advance
  },
  data() {
    return {
      score:4.0,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      activeName: 'first',
      activeName1:'first',
      movieinfo:{}
    }
  },
  mounted() {
    this.getmovieinfo(this.$route.query.moviename)
    this.$store.commit("ChangePages",this.changepage)
  },
  methods: {
    getmovieinfo(moviename){
      this.$axios({
        url:"/movie/getMovieInfo",
        params:{
          moviename:moviename
        }
      }).then((res)=>{
        this.movieinfo=res.data
      })
    },
    player(movieinfo){
      // alert(movieinfo.uploadpath)
      this.$router.push({
        path:"/Index/Player",
        query:{
          moviename:movieinfo.moviename,
          uploadpath:movieinfo.uploadpath
        }
      })
    },
     handleClick(tab, event) {
        console.log(tab, event);
      },
     changepage(value){
        this.activeName=value
     }
  },
}
</script>
<style scoped>
.big-box {
  width: 100%;
  height: 100%;
  background-color:rgb(255, 255, 255);
}
.big-box .header {
  width: 100%;
  height: 57%;
  color: rgb(255, 255, 255);
  background-image: linear-gradient(to right, #eea2a2 0%, #bbc1bf 19%, #57c6e1 42%, #b49fda 79%, #7ac5d8 100%);
  position: relative;
  z-index: 10;
}
.big-box .main {
  width: 100%;
  display: flex;
  flex-flow: row nowrap;
  margin-top: 40px;
  justify-content: space-between;
}
.big-box .header .image {
  width: 200px;
  height: 300px;
  position: absolute;
  top: 70px;
  left: 200px;
}
.big-box .header .image img {
  width: 100%;
  height: 100%;
}
.big-box .header .info {
  width: 300px;
  height: 280px;
  position: absolute;
  top: 70px;
  left: 420px;
  
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}
.big-box .header .score {
  width: 200px;
  height: 200px;
  position: absolute;
  top:250px;
  left: 750px;
}
.big-box .header .score-show {
  display: flex;
  flex-flow: row;
  justify-content: flex-start;
}
.intr {
  width: 50%;
  margin-left: 10%;
}
.otherinfo {
  width: 30%;
  height: 100px;
  /* background-color: #7ac5d8; */
}
</style>