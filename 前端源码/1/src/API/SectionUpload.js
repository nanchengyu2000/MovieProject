import axios from "axios";
axios.defaults.baseURL=""
const Slice={
list:[], //数据列表
sliceUp(flie){
  let size=flie.size  //获取文件大小
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
//单片上传  param指的是单片
uploadSplice(param,url){
  let _this=this
  let formData=new FormData()
  formData.append("size",param.size)
  formData.append("current",parent.size)
  formData.append("file",param.file)
  axios({
    headers:{
      "Context-type":"multipart/form-data"
    },
    url:url,
    data:formData
  }).then((res)=>{
      if (!res.data.state) {    //返回一个对象，state表示上传转态，count表示当前切片位置
        for (let i = 0; i < _this.list.length; i++) {
          if (_this.list.current==res.data.count) {
            this.uploadSplice
          }
        }
      }
  })
} 
}

export default Slice