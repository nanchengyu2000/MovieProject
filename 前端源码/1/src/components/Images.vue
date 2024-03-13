<template>
  <div class="box">
    <div v-for="(file,index) in files" :key="index" class="image">
      <img :src="resourcesPath+file" alt="">
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      files:[],
      resourcesPath:this.$resourcesPath
    }
  },
  props:["url"],
   watch:{
    url(newvalue,oldvalue){
      if (newvalue!=undefined) {
        this.getFlies(newvalue+"\\剧照")
      }
    }
  },
  methods: {
    getFlies(url){
      this.$axios({
        url:"/movie/getFlies",
        params:{
          path:url
        }
      }).then((res)=>{
        if (res.data.length) {
            for (let i = 0; i < res.data.length; i++) {
                this.files.push(res.data[i])
            }
          }
      })
    },
  },
}
</script>
<style scoped>
.box {
  width: 100%;
  height: auto;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
}
.box .image {
  width: 100px;
  height: 100px;
  margin-left: 20px;
  margin-bottom: 20px;
}
.box .image img{
  width: 100%;
  height: 100%;
}
</style>