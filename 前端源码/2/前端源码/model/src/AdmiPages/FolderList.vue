<template>
  <div class="box" @click="closeMenu" @contextmenu.prevent="openMenu($event)">
    <div class="folder" v-for="(folder,index) in folders" :key="folder.id"  @contextmenu.prevent.stop="openMenu1($event,index,folder)">
      <img style="width:100px;height:100px" @click="addpath(folder)" src="../assets/文件夹.png" alt="">
      <span @dblclick="folder.input=!folder.input" v-show="!folder.input">{{folder.name}}</span>
      <el-input v-show="folder.input" @focus="oldvalue(folder.name)" @blur="changename(folder.name)" type="text" v-model="folder.name"></el-input>
      <ul v-show="visible1" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
        <li @click="deletedir">删除文件夹</li>
        <li @click.stop="rechristen">重命名</li>
    </ul>
    </div>
    <ul v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
        <li @click="createdir">创建文件夹</li>
    </ul>
  </div>
</template>
<script>
import {Message} from "element-ui"
export default {
  data() {
    return {
      visible: false,
      visible1: false,
      top: 0,
      left: 0,
      folders:[],
      id:0,
      dircount:"",
      paths:[],
      folder:"",
      oldname:"",
      dirname:"",
      administratorname:"",
    }
  },
  mounted() {
    this.administratorname=JSON.parse(sessionStorage.getItem("administratorinfo")).administratorname
    this.getdir()
  },
  methods: {
    getdir(){
      this.$axios({
        url:"/movie/getdir",
        params:{
          path:"\\"+this.administratorname
        }
      }).then((res)=>{
        if (res.data.length) {
          for (let i = 0; i < res.data.length; i++) {
            let num=this.folders.filter((item)=>{
              return item.name==res.data[i]
            })
            if (num.length==0) {
              this.folders.push({
            id:i,
            name:res.data[i],
            input:false
          })
            }
          }
          this.id=res.data.length
        }
        // this.loading.close()
      })
    },
    changename(newname){
      this.$axios({
        url:"/movie/changedirname",
        params:{
          username:this.administratorname,
          newname:newname,
          oldname:this.oldname
        }
      }).then((res)=>{
        if (res.data) {
          this.folder.input=false
        }
      })
      
    },
    oldvalue(oldname){
      this.oldname=oldname
    },
    openMenu(e) {
      var x = e.clientX;
      var y = e.clientY;
      this.top = y;
      this.left = x;
      this.visible = true;//在这里控制右键菜单的打开
    },
    deletedir(){
      this.$axios({
        url:"/movie/rmdir",
        params:{
          username:this.administratorname,
          filename:this.dirname
        }
      }).then((res)=>{
        if (res.data==1) {
          this.folders.splice(this.dircount,1)
          // this.getdir()
        }else if(res.data==0){
          Message.error({
            message:"删除失败",
            type:"error"
          })
        }else if(res.data==2){
          Message.warning({
            message:"正在切片不能删除！",
            type:"warning"
          })
        }
      })
    },
    rechristen(folder){
      this.folder.input=!this.folder.input
      this.visible1 = false
    },
    openMenu1(e,index,folder) {
      var x = e.clientX;
      var y = e.clientY;
      this.top = y;
      this.left = x;
      this.visible1 = true;//在这里控制右键菜单的打开
      this.dircount=index
      this.folder=folder
      this.dirname=folder.name
    },
    closeMenu() {
      this.visible = false;
      this.visible1 = false;
    },
    createdir(){
      const data=new Date().valueOf()
      this.$axios({
        url:"/movie/create",
        params:{
          filename:"文件夹"+data,
          username:this.administratorname
        }
      }).then((res)=>{
        if (res) {
          this.folders.push({
        id:this.id,
        name:"文件夹"+data,
        input:false
      })
      this.id++
        }
      })
    },
     addpath(folder){
      this.$router.push({
        path:"/IndexAdmi/UploadMovie/FileList",
        query:{
          parentname:folder.name
        }
      })
    }
  },
}
</script>
<style scoped>
.box {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-content: flex-start;
}
.folder {
  height: 120px;
  margin-right: 20px;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
  align-items: center;
}
.el-input {
  width: 100px;
}
.contextmenu {
  margin: 0;
  background: #fff;
  z-index: 3000;
  position: absolute;
  list-style-type: none;
  padding: 5px 0;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  color: #333;
  box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
}

.contextmenu li {
  margin: 0;
  padding: 7px 16px;
  cursor: pointer;
}
.contextmenu li:hover {
  background: rgb(177, 50, 50);
}
</style>