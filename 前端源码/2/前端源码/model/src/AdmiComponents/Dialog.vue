<template>
  <el-dialog title="管理员面板" :visible.sync="dialogFormVisible">
     <!-- <el-tabs v-model="activeName" @tab-click="handleClick"> -->
     <el-tabs v-model="activeName">
    <el-tab-pane label="更改基本信息" name="first">
        <el-row style="height:150px" :gutter="10">
          <el-col span="6">
            <ImgeUp/>
          </el-col>
          <el-col span="4">
            <el-button style="margin-top:50%" @click="changeheader" size="small" type="primary">确定</el-button>
          </el-col>
        </el-row>     
    </el-tab-pane>
  </el-tabs>
  <div slot="footer" class="dialog-footer">
    <el-button @click="cancel">关闭</el-button>
    <el-button type="primary" @click="confirm">确定</el-button>
  </div>
</el-dialog>  
</template>
<script>
import ImgeUp from './ImgeUp.vue'
// import {Message} from 'element-ui'
export default {
  inject:['reload'],
  data() {
    return {
        formLabelWidth: '120px',
        activeName:"first",
        userinfo:{}
    }
  },
  components:{
      ImgeUp
  },
   methods:{
    changeheader(){
      this.$store.state.dialog.changeheader()
      this.reload();
    },
    change(value){
      localStorage.setItem("selectUserItem",value)
      this.$store.state.Item.changNum(localStorage.getItem("selectUserItem"))
      this.$store.state.Item.changAsidea(localStorage.getItem("selectUserItem"))
    },
    cancel(){
     this.$store.commit("disappear");
     this.$axios({
      url:"/User/getUser",
      params:{
        name:sessionStorage.getItem("usename")
      }
    }).then((res)=>{
      let userinfo=JSON.stringify(res.data)
      localStorage.setItem("userinfo",userinfo)
    })
    },
    confirm(){
      //在后台更改数据
      this.$store.commit("disappear");
      this.$axios({
      url:"/User/getUser",
      params:{
        name:sessionStorage.getItem("usename")
      }
    }).then((res)=>{
      let userinfo=JSON.stringify(res.data)
      localStorage.setItem("userinfo",userinfo)
    })
    },
   },
   mounted(){
    // this.$axios({
    //   url:"/member/getUser",
    //   params:{
    //     name:sessionStorage.getItem("usename")  //获取登录时的名称
    //   }
    // }).then((res)=>{
    //   this.userinfo=res.data 
    // })
   },
   computed:{
      dialogFormVisible(){
        return this.$store.state.dialog.dialogFormVisible
      },
      name(){
        return sessionStorage.getItem("usename")
      }
   }
}
</script>
<style scoped>
.input {
  width: 200px;
  height: 20px;
  border: none;
  border-bottom: 1px solid black;
  outline: none;
}
.el-col {
  height: 50px;
}
</style>