<template>
    <div class="box">
    <el-card>
      <i class="el-icon-bell"></i>
        海豚影院为您提供《{{movieinfo.moviename}}》免费在线观看地址，影片主演列表、影片相关资讯、海报图片、上映更新时间、主演其他影片、 以及《{{movieinfo.moviename}}》剧情等内容。影片播放地址：如果喜欢 
        《{{movieinfo.moviename}}》这部影片，分享链接
        <a :href="location">{{location}}</a>。
    </el-card>
        <div class="player">
          <video @loadedmetadata="loadeddata" @timeupdate="timeupdate" id="videoPlayer" class="video-js" muted></video>
          <div  class="movieinfo">
            <span class="moviename">
              {{movieinfo.moviename}}
            </span>
            <div class="moviedata">
              <span>{{movieinfo.moviearea}}</span>
              <span>
                <el-rate 
                  v-model="movieinfo.grade"
                  show-score
                  disabled
                  text-color="#ff9900"
                  score-template="{value}"></el-rate>
              </span>
            </div>
          </div>
  </div>
  <div class="givecore">
    <span v-if="!isgrade" style="margin-right:20px">海豚影院欢迎您给出评分:</span>
    <span v-else style="margin-right:20px">您给出评分:</span>
    <el-rate 
    :disabled="isgrade"
    v-model="score"
    show-score
    @change="givescore"
    text-color="#ff9900"
    score-template="{value}"></el-rate>
  </div>
  </div>
</template>
<script>
import Videojs from "video.js";
import {Message,Loading} from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
export default {
  data() {
    return {
      movieinfo:{},
      currentTime:0,
      userinfo:{},
      location:"",
      score:0,
      resourcesPath:this.$resourcesPath,
      nowPlayVideoUrl:"",
      isgrade:false,
      loading:"",
    }
  },
  mounted() {
      this.loading=Loading.service({
          lock: true,
          text: '加载中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
    this.userinfo=JSON.parse(localStorage.getItem("userinfo"))
    this.getmovieinfo(this.$route.query.moviename)
    this.gethistory(this.userinfo.userid,this.$route.query.movieid)
    this.getscore()
  },
  methods: {
    givescore(){
      this.$axios({
        url:"/movie/givescore",
        params:{
          userid:this.userinfo.userid,
          movieid:this.movieinfo.movieid,
          score:this.score
        }
      }).then((res)=>{
          if (res.data) {
            Message.success({
              message:"评分成功!",
              type:"success"
            })
            this.isgrade=true
          }else {
            Message.error({
              message:"评分失败!",
              type:"error"
            })
          }
      })
    },
    getscore(){
      this.$axios({
        url:"/movie/getscore",
        params:{
          userid:this.userinfo.userid,
          movieid:this.$route.query.movieid,
        }
      }).then((res)=>{
          if (res.data!=11.0) {
            this.score=res.data
            this.isgrade=true
          }
      })
    },
    initVideo(nowPlayVideoUrl) {
     // 这些options属性也可直接设置在video标签上，见 muted
     let options = {
     autoplay: true, // 设置自动播放
     controls: true, // 显示播放的控件
     sources: [
     // 注意，如果是以option方式设置的src,是不能实现 换台的 (即使监听了nowPlayVideoUrl也没实现)
     {
     src: nowPlayVideoUrl,
     type: "application/x-mpegURL" // 告诉videojs,这是一个hls流
     }
     ]
     };
     // videojs的第一个参数表示的是，文档中video的id
     const myPlyer = Videojs("videoPlayer", options, function onPlayerReady() {
     console.log("onPlayerReady 中的this指的是：", this); // 这里的this是指Player,是由Videojs创建出来的实例
     console.log(myPlyer === this); // 这里返回的是true
     });
     },
    getmovieinfo(moviename){
      this.$axios({
        url:"/movie/getMovieInfo",
        params:{
          moviename:moviename
        }
      }).then((res)=>{
        if (res.data) {
          this.movieinfo=res.data 
          this.nowPlayVideoUrl=this.resourcesPath+res.data.moviepath
          this.initVideo(this.nowPlayVideoUrl);
        }
        this.loading.close()
      })
    },
    timeupdate(event){
      this.currentTime=event.target.currentTime
      this.sethistory()
    },
    sethistory(){
        this.$axios({
          url:"/movie/sethistory",
          params:{
            userid:this.userinfo.userid,
            movieid:this.movieinfo.movieid,
            historytime:this.currentTime
          }
        }).then((res)=>{

        })
    },
    gethistory(userid,movieid){
      this.$axios({
          url:"/movie/gethistory",
          params:{
            userid:userid,
            movieid:movieid,
          }
        }).then((res)=>{
          if (res.data) {
            this.currentTime=res.data
          }
        })
    },
    loadeddata(event){
      event.target.currentTime=this.currentTime
    },
  },
}
</script>
<style scoped>
.box {
  width: 100%;
  height: 100%;
  /* background:rgb(21, 21, 21); */
}
.givecore {
  width: 400px;
  height: 100px;
  margin-left: 50%;
  transform: translateX(-50%);
  margin-top: 20px;
  display: flex;
  flex-flow: row nowrap;
  font-size: 20px;
  font-weight: 700;
  color: #fff;
}
.el-card {
  width: 70%;
  height: auto;
  position: relative;
  top: 20px;
  left: 50%;
  color: #fff;
  background:rgb(21, 21, 21);
  transform: translateX(-50%);
  border-radius: 10px;
}
.moviename {
  font-size:20px;
  font-weight:700;
  color:white;
  margin-left:20px;
  margin-top:20px;
  margin-bottom: 10px;
}
.moviedata {
  color:white;
  margin-left:20px;
  margin-top: 5px;
  font-style: italic;
}
.player {
  width: 80%;
  height: auto;
  margin-top: 50px;
  margin-left: 15%;
  display: flex;
  flex-flow: row nowrap;
}
.player .movieinfo {
  width: 15%;
  height: auto;
  display: flex;
  flex-flow: column;
}
#videoPlayer {
    width: 80%;
    height: 400px;
}
</style>