<template>
  <div>
    <el-row class="home" style="height:80vh" :gutter="20">
    <el-col style="margin-top: 20px" :span="8">
      <el-card body-style="padding:0px;height:300px">
        <div class="user">
          <img v-show="imgorvideo" :src="resourcesPath+administratorinfo.img" />
          <div v-show="!imgorvideo" class="img">
          <video loop preload="true" :src="resourcesPath+administratorinfo.img" autoplay></video>
        </div>
        <div class="userinfo">
            <p class="name">用户名：{{administratorinfo.administratorname}}</p>
        </div>
        </div>
      </el-card>
      <el-card body-style="padding:0px;height:250px;">
        <h3 style="margin:20px 20px">日志:</h3>
      </el-card>
    </el-col>
    <el-col style="margin-top: 20px;" :span="16">
        <!-- 展示借阅书籍数据 -->
         <div  class="charts"
        ref="bar"
        style="width:900px;height:350px;margin-left: 50px"></div>
    </el-col>
  </el-row>
  <el-row>
    
  </el-row>
  </div>
</template>



<script>
import {Loading,Notification} from 'element-ui'
var load=""
export default {
  computed:{
    name(){
      return sessionStorage.getItem("usename")
    },
    header(){
      return this.userinfo.header
    },
  },
  methods:{
       // 柱状图
    bar () {
      // 初始化echarts实例，init参数为要绑定的dom元素，通过this.$refs方法获取dom
      let myChart = this.$echarts.init(this.$refs.bar)
      // 指定图表的配置项和数据
      let option = {
        xAxis: {
          data: ['A', 'B', 'C', 'D', 'E']
        },
        yAxis: {},
        series: [
        {
          data: [10, 22, 28, 43, 49],
          type: 'line',
          stack: 'x'
        },
        {
          data: [5, 4, 3, 5, 10],
          type: 'line',
          stack: 'x'
        }
  ]
};
        
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option)
      },
    getuserinfo(username){
        this.$axios({
          url:"/member/getAdmi",
          params:{
            name:username
          }
        }).then((res)=>{
          if (res.data) {
            this.administratorinfo=res.data
            this.selectContext(res.data.img)
            sessionStorage.setItem("administratorinfo",JSON.stringify(res.data))
          }
        })
    },
    selectContext(value){   //管理员头像格式
      var suffix = value.substr(value.lastIndexOf(".")+1);
      if (suffix=="mp4") {
        this.imgorvideo=false
      }else {
        this.imgorvideo=true
      }
    },
  },
  mounted(){
      // 调用了methods中的bar方法
      if (this.$route.query.success) {
        Notification({
          title: '系统',
          message:this.$route.query.name+"，成功登录系统!欢迎您的到来！",
          type: 'success'
        });
      this.getuserinfo(this.$route.query.name)
      this.$router.push({query:{}})
    }else {
      this.administratorinfo=JSON.parse(sessionStorage.getItem("administratorinfo"))
    }
        this.bar();
    },
  data() {
    return {
      administratorinfo:{},  
      chart: null,
      imgorvideo:true,
      resourcesPath:this.$resourcesPath
    };
  },
};
</script>

<style scoped>
#he-plugin-standard {
  display: flex;
  flex-flow: row;
  justify-content: center;
  align-content: center;
}
.el-card {
  margin-bottom: 20px;
}
.el-card .user {
  height: 100%;
  border-bottom: 1px solid #ccc;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-around;
  align-items: center;
}
.el-card .user img {
  width: 170px;
  height: 170px;
  border-radius: 50%;
}
.el-card .user .img {
  width: 170px;
  height: 170px;
  border-radius: 50%;
  background-color: black;
  overflow: hidden;
  position: relative;
}
.el-card .user video {
  width: 100%;
  height: 100%;
}
.el-card .user .userinfo p {
  height: 20px;
}
.el-card .loginfo {
  width: 100%;
  height: 100px;
  text-align: center;
}
.el-card .loginfo p {
  margin-bottom: 5px;
  height: 15px;
  font-size: 10px;
  
}
.el-card .user .userinfo {
  margin-left: 10px;
}

.el-card .user .userinfo .access {
  color: burlywood;
}
.order {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
}
.order .el-card {
  width: 350px;
  height: 100px;
  margin-bottom: 10px;
  display: flex;
}
.order .el-card i {
  width: 30%;
  height: 100px;
  text-align: center;
  font-size: 50px;
  line-height: 100px;
}
.order .el-card .info {
  width: 70%;
  height: 100px;
  margin-top: 20px;
}
.order .el-card .info li {
  list-style: none;
}
</style>