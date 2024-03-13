<template>
  <div>
    <div>
      <input type="file" ref="upload" accept=".mp4,.u3m8" style="display:none" multiple @change="select($event)">
      <span @click="upload">点击上传视频</span>
      <el-progress :percentage="allpercent"></el-progress>
    </div>
    </div>
</template>
<script>
import {Message} from "element-ui"
var basicpercent=0
export default {
  data() {
    return {
      list:[],
      upLoadProgress:0,
      slice:1024*1024,
      percent:0,
      size:0,
      allpercent:0,
      username:"",
      filename:"",
      moviename:sessionStorage.getItem("moviename")
    }
  },
  mounted() {
    this.username=JSON.parse(sessionStorage.getItem("administratorinfo")).administratorname
    this.filename=this.$store.state.movieinfo.parentname
  },
  methods: {
    async select(e){
      this.sliceUp(e.target.files[0])
      let i=0
        let flag=true
        while(flag){
          let fromDate=new FormData()
          fromDate.append("movie",this.list[i].file)
          fromDate.append("count",this.list[i].count)
          fromDate.append("current",this.list[i].current)
          fromDate.append("username",this.username)
          fromDate.append("filename",this.filename)
          fromDate.append("moviename",this.moviename)
          i++
          if (i>=this.list.length) {
            flag=false
          }
          await  this.$axios({
            headers:{
            "Content-type":"multipart/form-data"
            },
            method:"post",
            url:"/movie/uploadPath",
            data:fromDate,
            onUploadProgress:(progressEvent)=>{
                basicpercent=progressEvent.loaded / progressEvent.total*100//实时获取上传进度
                if (basicpercent==100) {
                     this.percent++
                      var temp=this.percent/this.size*100
                      this.allpercent=Math.floor(temp)
                 }
          }
        }).then((res)=>{
            if (res.data==1) {
              Message.success({
                message:"上传成功!",
                type:"success"
              })
            }
        })
        
      }
      
    },
    upload(){
      this.$refs.upload.click()
    },
    sliceUp(file){
        let size=file.size  //获取文件大小
        let count=Math.ceil(size/this.slice)   //每份切片大小为1024b /1kb  1024*1024*1024  /1mb  /100mb
        for (let i = 0; i < count; i++) {
          let item={
            count:count,
            current:i,
            file:file.slice(i*this.slice,(i+1)*this.slice)
        }
        this.size=count
        this.list.push(item)
      }
    }
  },
}
</script>
<style scoped>

</style>