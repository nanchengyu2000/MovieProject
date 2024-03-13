<template>
  <div class="box" @click="closeMenu" @contextmenu.prevent="openMenu($event)">
    <div class="fileList">
      <div class="folder" v-for="(file,index) in files" :key="index">
          <img :src="resourcesPath+file.path"/>
      </div>
    </div>
  </div>
</template>
<script>
import {Message} from 'element-ui'
export default {
  data() {
    return {
      files:[],
      administratorname:"",
      relativepath:"",
      visible:false,
      resourcesPath:this.$resourcesPath
    }
  },
  mounted() {
    this.administratorname=JSON.parse(sessionStorage.getItem("administratorinfo")).administratorname
    this.parentname=this.$route.query.parentname
    this.$store.commit("setrelativepath",this.$route.query.relativepath)
    this.getFlies()
  },
  methods: {
    getFlies(){
      this.$axios({
        url:"/movie/getFlies",
        params:{
          path:"\\"+this.administratorname+"\\"+this.parentname
        }
      }).then((res)=>{
        if (res.data.length) {
          res.data[0].subString
          for (let i = 0; i < res.data.length; i++) {
            this.files.push({
            id:i,
            path:res.data[i],
          })
          }
        }
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