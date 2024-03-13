<template>
  <div class="box" @click="closeMenu" @contextmenu.prevent="openMenu($event)">
    <ul v-show="visible" :style="{left:left+'px',top:top+'px'}" class="contextmenu">
        <li @click="create">添加剧照</li>
    </ul>
      <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
        <!-- action="https://vyke0kcd.beesnat.com/Movie-System/movie/uploadStage" -->
        <!-- http://localhost:8084/System/movie/uploadStage -->
          <el-upload
            class="upload-demo"
            action="https://vyke0kcd.beesnat.com/Movie-System/movie/uploadStage"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :on-success="success"
            :on-error="error"
            name="Stage"
            :data="other"
            :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
      </el-dialog>     
    <div class="fileList">
      <div class="folder" v-for="(file,index) in files" :key="index">
          <img  :src="resourcesPath+file" />
      </div>
    </div>
  </div>
</template>
<script>
import {Message,Loading} from 'element-ui'
export default {
  data() {
    return {
      files:[],
      administratorname:"",
      relativepath:"",
      visible:false,
      disable:true,
      dialogTableVisible:false,
      fileList:[],
      other:{
        path:""
      },
      resourcesPath:this.$resourcesPath,
      loading:"",
      left:"",
      top:""
    }
  },
  mounted() {
    this.loading=Loading.service({
          lock: true,
          text: '加载中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
    this.other.path=this.$route.query.parentname
    this.getFlies()
  },
  methods: {
    handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      success(response){
        if (response=="true") {
          this.getFlies()
        }
      },
      error(){
        Message.warning({
          message:"一张剧照上传失败！",
          type:"warning"
        })
      },
    getFlies(){
      this.$axios({
        url:"/movie/getFlies",
        params:{
          path:this.other.path
        }
      }).then((res)=>{
         this.files=res.data
         this.loading.close()
      })
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
    closeMenu() {
      this.visible = false;
    },
    create(){
      this.dialogTableVisible=true
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
  flex-flow: row wrap;
  justify-content: flex-start;
  align-content: flex-start;
}
.fileList .folder {
  width:200px;
  height:150px;
  overflow: hidden;
  margin-right: 20px;
  margin-bottom: 10px;
}
.fileList .folder img {
  width: 100%;
  height: 100%;
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