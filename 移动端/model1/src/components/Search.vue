<template>
  <div class="search">
    <input type="text" @keydown.enter="onSearch">
    <van-button icon="search" type="primary" />
  </div>
</template>
<script>
export default {
  data() {
    return {
      value:''
    }
  },
  methods: {
    onSearch(){

    }
  },
}
</script>
<style scoped>
.search {
  width: 80%;
  height: 2rem;
  margin-left: 10%;
  display: flex;
  flex-flow: row nowrap;
}
.search input {
  width: 80%;
  height: 80%;
  border: 1px solid #ccc;
  outline: none;
  border-top-left-radius: 1rem;
  border-bottom-left-radius: 1rem;
  background: #ccc;
}
.van-button {
  height: 90%;
  border: none;
  border-top-right-radius: 1rem;
  border-bottom-right-radius: 1rem;
  background: #ccc;
}
</style>