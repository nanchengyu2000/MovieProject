<template>
  <div class="box" @click="closeMenu" @contextmenu.prevent="openMenu($event)">
    <ul v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
        <li @click="create">创建视频</li>
    </ul>
    <div class="fileList">
      <div class="folder" v-for="(folder,index) in folders" :key="index">
      <img style="width:100px;height:100px" @click="opendir(folder)" src="../assets/文件夹.png" alt="">
      <span>{{folder.name}}</span>
    </div>
    </div>
    
    <el-dialog title="上传视频" :close-on-click-modal="false" :visible.sync="dialogFormVisible">
      <el-steps :active="active" simple>
      <el-step title="步骤 1" icon="el-icon-edit"></el-step>
      <el-step title="步骤 2" icon="el-icon-upload"></el-step>
    </el-steps>
       <el-card style="width:100%;height:100%">
          <FirstStep v-if="nex"/>
          <ScendStep v-if="!nex"/>
       </el-card>
       <div slot="footer" class="dialog-footer">
          <el-button @click="exit" v-show="quit">取消</el-button>
          <el-button type="primary" v-show="nex" @click="next">下一步</el-button>
          <el-button type="primary" v-show="!nex" @click="success">完成</el-button>
        </div>
    </el-dialog>
  </div>
</template>
<script>
import {Message,Loading} from 'element-ui'
import FirstStep from '../AdmiComponents/FirstStep.vue'
import ScendStep from '../AdmiComponents/ScendStep.vue'
export default {
  components:{
    FirstStep,
    ScendStep
  },
  data() {
    return {
      visible: false,
      visible1: false,
      dialogFormVisible:false,
      nex:true,
      folders:[],
      active:1,
      administratorname:"",
      parentname:"",
      disable:true,
      quit:true,
      loading:"",
      top:"",
      left:""
    }
  },
  mounted() {
    this.loading=Loading.service({
          lock: true,
          text: '加载中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
    this.administratorname=JSON.parse(sessionStorage.getItem("administratorinfo")).administratorname
    this.parentname=this.$route.query.parentname
    this.$store.commit("setparentname",this.$route.query.parentname)
    this.getdir()
  },
  methods: {
    getdir(){
      this.$axios({
        url:"/movie/getdir",
        params:{
          path:"\\"+this.administratorname+"\\"+this.parentname
        }
      }).then((res)=>{
        if (res.data.length) {
          for (let i = 0; i < res.data.length; i++) {
            if (res.data[i]!='影视') {
              this.folders.push({
              id:i,
              name:res.data[i],
            })
            }
          }
        }
        this.loading.close()
      })
    },
    create(){
      this.disable=false 
      this.active=1
      this.nex=true
      this.dialogFormVisible=true
    },
    openMenu(e) {
      if (this.disable) {
        var x = e.clientX;
        var y = e.clientY;
        this.top = y;
        this.left = x;
        this.visible = true;//在这里控制右键菜单的打开
      }
    },
    opendir(folder){
      this.$store.commit("addpath",{value:"/IndexAdmi/UploadMovie/FileManage",name:folder.name})
      
      switch(folder.name){
        case "剧照":
          this.toOpen(folder,"StagePhoto")
          break;
        case "基础信息":
          this.$router.push({
            path:"/IndexAdmi/UploadMovie/BasicInfo",
            query:{
              moviename:this.parentname
            }
          })
          break;
        case "导演集合":
          this.toOpen(folder,"Director")
          break;
        case "影视":
          this.toOpen(folder,"Television")
          break;
        case "预告片":
          this.toOpen(folder,"Preview")
          break;
      }
    },
    toOpen(folder,path){
      this.$router.push({
        path:"/IndexAdmi/UploadMovie/"+path,
        query:{
          parentname:"\\"+this.administratorname+"\\"+this.parentname+"\\"+folder.name
        }
      })
    },
    closeMenu() {
      this.visible = false;
    },
    exit(){
      this.disable=true
      this.dialogFormVisible = false
    },
    next(){
      let movieinfo=this.$store.state.movieinfo.movie;
      movieinfo.moviename=this.parentname
      if (movieinfo.moviename==""||movieinfo.moviearea==""||movieinfo.sort==""||movieinfo.duration==""||movieinfo.dateissued=="") {
        Message.warning({
          message:"信息不全！",
          type:"warning"
        })
      }else{
       let movie=JSON.stringify(movieinfo)  
      this.$axios({
        method:"POST",
        url:"/movie/addmovie",
        headers:{
          "Content-type":"application/json"
        },
        data:movie,
      }).then((res)=>{
          if (res.data) {
            sessionStorage.setItem("moviename",movieinfo.moviename)
            this.nex=false,
            this.active=2
            this.quit=false
        }else {
          Message.error({
            message:"创建失败!",
            type:"error"
          })
        }
    })
      }
  },
    success(){
      this.dialogFormVisible=false
      this.show=true
      this.disable=false
      this.quit=true
      this.getdir()
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
}
.fileList {
  width: 100%;
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
}
.fileList .folder {
  height: 120px;
  margin-right: 20px;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
  align-items: center;
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