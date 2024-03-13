<template>
  <van-tabbar v-model="active"  @change="onChange">
  <van-tabbar-item v-for="(tabber,index) in tabbers" :key="index" replace :to="tabber.to">
    <span>{{tabber.title}}</span>
    <template #icon="props">
      <img :src="props.active ? tabber.active : tabber.inactive" />
    </template>
  </van-tabbar-item>
</van-tabbar>
</template>
<script>
export default {
  data() {
    return {
      active:0,
      tabbers:[{
        active:require('../assets/首页 (1).png'),
        inactive:require('../assets/首页.png'),
        title:"首页",
        to:"/",
      },{
        active:require('../assets/会员 (1).png'),
        inactive:require("../assets/会员.png"),
        title:"会员",
        to:"/Member",
      },{
        active:require('../assets/我 (1).png'),
        inactive:require("../assets/我.png"),
        title:"我的",
        to:"/OneSelf",
      },]
    }
  },
  mounted() {
    this.active=sessionStorage.getItem("active")
  },
  methods: {
     onChange() {
      sessionStorage.setItem("active",this.active)
    },
  },
}
</script>
<style scoped>
.van-tabbar {
  position: fixed;
  width: 90%;
  margin-left: 5%;
  bottom: 0rem;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-around;
}
</style>