<template>
  <div  class="big_box">
  <div v-show="flag" class="box">
    <div class="aside_left">
      <img src="../assets/互动娱乐.png" alt="">
    </div>
    <div class="aside_right">
      <div class="small_box">
        <div ref="loginUser" v-show="!Administrator" class="login animated fadeInLeft">
        <div class="title" style="font-size:20px;font-weight:700;margin-top:10px;display: flex;flex-flow: row nowrap;justify-content: space-between;">
          <span>快看影院，欢迎您的到来！</span>
          <span>
            <el-link @click="flag=false" type="primary">注册</el-link>
          </span>
        </div>
        <div  class="type" style="font-size:20px;font-weight:700;margin-top:10px">
          登录
        </div>
      <el-form v-model="data" label-width="80px" style="margin-top:20px;width:80%">
        <el-form-item label="账号:" >
          <el-input v-model="data.username"></el-input>
        </el-form-item>
        <el-form-item label="密码:" >
          <el-input show-password v-model="data.password"></el-input>
        </el-form-item>
      <div style="display: flex;flex-flow: row nowrap;margin-left:40px;margin-bottom:20px">
        <el-button @click="logon" ref="loginbutton" style="background:rgb(58, 58, 58);color:#FFFF;width:150px">登录</el-button>
        <el-button type="warning" @click="changeJur">我是管理员<i class="el-icon-right"></i></el-button>
      </div>
      </el-form>
      <div class="other_login">
        <span class="title" style="font-size:20px;font-weight:700;margin-right:10px">其他登录方法:</span>
        <span class="icon2">
          <img src="../assets/qq.png" alt="">
        </span>
      </div>
        </div>
        <div ref="loginAdmi" v-show="Administrator" class="login animated fadeInLeft">
        <div class="title" style="font-size:20px;font-weight:700;margin-top:10px;display: flex;flex-flow: row nowrap;justify-content: space-between;">
          <span>后台管理系统，欢迎您的到来！</span>
        </div>
        <div  class="type" style="font-size:20px;font-weight:700;margin-tqop:10px">
          登录
        </div>
      <el-form v-model="Admidata" label-width="80px" style="margin-top:20px;width:80%">
        <el-form-item label="账号:" >
          <el-input v-model="Admidata.username"></el-input>
        </el-form-item>
        <el-form-item label="密码:" >
          <el-input show-password v-model="Admidata.password"></el-input>
        </el-form-item>
      <div style="display: flex;flex-flow: row nowrap;margin-left:40px;margin-bottom:20px">
        <el-button @click="LoginbyAdmi" style="background:rgb(58, 58, 58);color:#FFFF;width:150px">登录</el-button>
        <el-button type="warning" @click="changeJurs">我是用户<i class="el-icon-right"></i></el-button>
      </div>
      </el-form>
        </div>
      </div>
    </div>
  </div>
  <div v-show="!flag" class="box1">
    <div class="aside_left">
      <img src="../assets/模块搭建.png" alt="">
    </div>
    <div class="aside_right">
      <div class="small_box">
        <div class="login animated fadeInLeft">
        <div class="title" style="font-size:20px;font-weight:700;margin-top:10px;display: flex;flex-flow: row nowrap;justify-content: space-between;">
          <span>欢迎您的到来！</span>
          <span>
            <el-link @click="flag=true" type="primary">登录</el-link>
          </span>
        </div>
        <div class="type" style="font-size:20px;font-weight:700;margin-top:10px">
          注册
        </div>
      <el-form v-model="data1" label-width="80px" style="margin-top:20px;width:80%">
        <el-form-item label="账号:" >
          <el-input v-model="data1.username"></el-input>
        </el-form-item>
        <el-form-item label="密码:" >
          <el-input show-password v-model="data1.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" >
          <el-input show-password v-model="data1.repassword"></el-input>
        </el-form-item>
      <el-form-item>
        <el-button @click="login" style="background:rgb(58, 58, 58);color:#FFFF;width:150px">注册</el-button>
      </el-form-item>
      </el-form>
      
      <div v-show="flag" class="other_login">
        <span class="title" style="font-size:20px;font-weight:700;margin-right:10px">其他登录方法:</span>
        <span class="icon1">
          <img src="../assets/微信.png" alt="">
        </span>
        <span class="icon2">
          <img src="../assets/qq.png" alt="">
        </span>
      </div>
      </div>
      </div>
    </div>
  </div>
  </div>
</template>
<script>
import {Message} from 'element-ui'
export default {
  data() {
    return {
      data:{
        username:"",
        password:"",
      },
      Admidata:{
        username:"",
        password:"",
      },
      data1:{
        username:"",
        password:"",
        repassword:"",
        email:""
      },
      flag:true,
      Administrator:false,
      // loginclass:"login"
    }
  },
  methods: {
    changeJur(){
      this.Administrator=true
    },
    changeJurs(){
      // this.$refs.loginUser.classList="login animated fadeInLeft"
      this.Administrator=false
    },
    login(){  //用户注册
      if (this.data1.username=="") {
        Message.error({
          message:"用户名不能为空",
          type:"error"
        })
      }else if(this.data1.password==""||this.data1.repassword==""){
        Message.error({
          message:"密码不能为空",
          type:"error"
        })
      }else if(this.data1.password!=this.data1.repassword){
        Message.error({
          message:"密码不正确",
          type:"error"
        })
      }else{
      this.$axios({
        url:"/log/login",
        params:{
          name:this.data1.username,
          password:this.data1.password
        }
      }).then((res)=>{
        if (res.data) {
          Message.success({
            message:"注册成功！",
            type:"success"
          })
          this.flag=true
        }else {
          Message.error({
            message:"注册失败！",
            type:"error"
          })
        }
      })
      }
    },
    logon(){    //用户登录
    if (this.data.username=="") {
        Message.error({
          message:"用户名不能为空",
          type:"error"
        })
      }else if(this.data.password==""){
        Message.error({
          message:"密码不能为空",
          type:"error"
        })
      }else{
      this.$axios({
        url:"/log/logon",
        params:{
          name:this.data.username,
          password:this.data.password
        }
      }).then((res)=>{
        if (res.data) {
          this.$router.replace({
            path:"/Index",
            query:{
              success:true,
              username:this.data.username
            }
          })
        }
        /* this.$router.push({
            path:"/Index/IndexLine",
            query:{
              success:true,
              username:this.data.username
            }
          }) */
      })
      }
    },
    LoginbyAdmi(){  //管理员登录
    if (this.Admidata.username=="") {
        Message.error({
          message:"用户名不能为空",
          type:"error"
        })
      }else if(this.Admidata.password==""){
        Message.error({
          message:"密码不能为空",
          type:"error"
        })
      }else{
      this.$axios({
        url:"/log/admiLogon",
        params:{
          name:this.Admidata.username,
          password:this.Admidata.password
        }
      }).then((res)=>{
        if (res.data) {
          this.$router.push({
            path:"/IndexAdmi/userHome",
            query:{
              success:true,
              name:this.Admidata.username
            }
          })
        }else {
          Message.error({
            message:"登录失败!",
            type:"error"
          })
        }
      })
      }
    }
  },
}
</script>
<style  scoped>
.big_box {
  width: 100%;
  height: auto;
  background-color: pink;
}
.big_box .box {
  width:80%;
  height: 600px;
  border-radius: 20px;
  position: absolute;
  background-color: rgb(242, 244, 255);
  top: 50px;
  /* transform: translateY(-50%); */
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-flow: row nowrap;
}
.big_box .box1 {
  width:80%;
  height: 600px;
  border-radius: 20px;
  position: absolute;
  background-color: rgb(232, 242, 250);
  top: 50px;
  /* transform: translateY(-50%); */
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-flow: row nowrap;
}
.big_box .box .aside_left {
  width: 50%;
}
.big_box .box .aside_left img{
  width: 100%;
  height: 100%;
}
.big_box .box .aside_right {
  width: 50%;
}
.big_box .box .aside_right .small_box {
    width: 90%;
    height: 70%;
    border-radius: 15px;
    margin-top: 100px;
    padding-top: 10px;
    background: rgb(238, 232, 240,0.5);
    box-shadow: 5px 5px rgba(131, 129, 132, 0.1);
}
.big_box .box .aside_right .login {
  width: 90%;
  margin-left: 5%;
  height: 80%;
}
.big_box .box1 .aside_left {
  width: 50%;
}
.big_box .box1 .aside_left img{
  width: 100%;
  height: 100%;
}
.big_box .box1 .aside_right {
  width: 50%;
}
.big_box .box1 .aside_right .small_box {
    width: 90%;
    height: 70%;
    border-radius: 15px;
    margin-top: 100px;
    padding-top: 10px;
    background: rgb(238, 232, 240,0.5);
    box-shadow: 5px 5px rgba(131, 129, 132, 0.1);
}
.big_box .box1 .aside_right .login {
  width: 90%;
  margin-left: 5%;
  height: 80%;
}
.other_login {
  width: 100%;
  height: 200px;
  display: flex;
  flex-flow: row nowrap;
  justify-content: center;
  align-content: center;
}
.other_login .icon1,.icon2 {
  width: 30px;
  height: 30px;
  margin-right:10px
}
.other_login .icon1 img {
  width: 100%;
  height: 100%;
}
.other_login .icon2 img {
  width: 100%;
  height: 100%;
}
</style>