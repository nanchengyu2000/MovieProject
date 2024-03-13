<template>
  <div>
    <el-upload
  class="upload-demo"
  action="http://vyke0kcd.beesnat.com//movie/uploadPath"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :before-remove="beforeRemove"
  multiple
  :auto-upload="false"
  :data="other"
  name="movie"
  :limit="1"
  :http-request="request"
  accept=".mp4"
  :on-exceed="handleExceed"
  :file-list="fileList">
  <el-button size="small" type="primary">点击上传电影</el-button>
  
</el-upload>
<el-button size="small" @click="submit" type="primary">点击上传</el-button>
  </div>
</template>
<script>
import Slice from  '../API/SectionUpload' 
  export default {
    data() {
      return {
        fileList: [],
        other:{
          moviename:sessionStorage.getItem("moviename"),
          username:"",
          filename:""
        },
        list:[],
        baseURL:this.$axios.defaults.baseURL
      };
    },
    mounted() {
      // console.log(Slice.sliceUp);
      this.other.username=JSON.parse(sessionStorage.getItem("administratorinfo")).administratorname
      this.other.filename=this.$store.state.movieinfo.parentname
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        
        this.sliceUp(file.raw)
        console.log(this.list);
      },
      sliceUp(file){
        console.log(file.raw);
        let size=file.size  //获取文件大小
        let count=Math.ceil(size/1024)   //每份切片大小为1024
        for (let i = 0; i < count; i++) {
          let item={
            size:1024,
            current:i,
            file:file.slice(i*1024,(i+1)*1024)
        }
        this.list.push(item)
      }
    },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      submit(){
        let file=this.fileList
        // Slice.sliceUp(file)
        console.log(file);
        // console.log(Slice.list.length);
      },
      request(){
        let file=this.fileList[0]
        console.log(file);
        // Slice.sliceUp(file)
        // console.log(Slice.list.length);
      }
    }
  }
</script>