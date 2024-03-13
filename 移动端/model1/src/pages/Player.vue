<template>
  <div id="app">
    <div class="player-container">
      <video id="videoPlayer" class="video-js" muted></video>
    </div>
  </div>
</template>
<script>
import Videojs from "video.js";

export default {
  name: 'App',
  mounted() {
    this.getMovieInfo(this.$route.query.moviename)
  },
  data () {
    return {
      nowPlayVideoUrl:"",
      MovieInfo:{},
      resource:this.$resource
    }
  },
  methods: {
    getMovieInfo(moviename){
      this.$axios({
        url:"/movie/getMovieInfo",
        params:{
          moviename
        }
      }).then((res)=>{
        this.MovieInfo=res.data
        this.nowPlayVideoUrl=this.resource+res.data.moviepath
        this.initVideo(this.nowPlayVideoUrl);
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
  },
}

</script>
