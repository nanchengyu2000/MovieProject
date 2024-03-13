<template>
  <div class="big-box">
    <div class="box">
      <router-view></router-view>  
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
    }
  },
  watch: {
    // visible(value) {
    //   if (value) {
    //     document.body.addEventListener('click', this.closeMenu)
    //   } else {
    //     document.body.removeEventListener('click', this.closeMenu)
    //   }
    // }
  },
  methods: {
    
   
  },  
}
</script>
<style scoped>
.big-box {
  width: 100%;
  height: 100%;
}
.box {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
}
</style>