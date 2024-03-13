<template>
  <div>
    <span style="width:300px">
        <el-form :model="movie">
          <el-form-item label="电影地区" :label-width="formLabelWidth">
            <el-input style="width: 200px;" v-model="movie.moviearea" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电影类型" :label-width="formLabelWidth">
            <span style="margin-right:10px">
              <el-tag size="small" style="margin-right:5px" v-for="(sor,index) in selectedsorts" :key="index" closable @close="handleClose(index)">{{sor}}</el-tag>
            </span>
            <el-select v-model="movie.sort" @change="addsort" placeholder="请选择电影类型">
              <el-option v-for="(sort,index) in sorts" :key="index" :label="sort" :value="sort"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电影时长" :label-width="formLabelWidth">
            <el-time-picker
                v-model="movie.duration"
                :picker-options="{
                  selectableRange: '00:00:00 - 3:00:00'
                }"
                placeholder="任意时间点">
            </el-time-picker>
          </el-form-item>
          <el-form-item label="中国上架时间" :label-width="formLabelWidth">
            <el-date-picker type="date" placeholder="选择日期" v-model="movie.dateissued" style="width: 200px;"></el-date-picker>
          </el-form-item>
          <el-form-item label="电影介绍" :label-width="formLabelWidth">
            <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 5}"
              size="medium"
              resize="none"
              placeholder="请输入内容"
              v-model="movie.intro">
            </el-input>
          </el-form-item>
        </el-form>
       </span>
  </div>
</template>
<script>
export default {
 data() {
  return {
      movie:{},
      formLabelWidth: '120px',
      selectedsorts:[],
      sorts:["动作","间谍","警匪","医学","武侠","古装","运动","战争","历史","灾难",'冒险',"西部","玄幻","奇幻","科幻","幻想","贺岁","犯罪",
            "黑帮","伦理","喜剧","爱情","恐怖","悬疑","青春","动画",'纪录',"戏曲","爱国","歌舞","记录"],
  }
 }, 
 mounted() {
  this.movie=this.$store.state.movieinfo.movie
 },
 methods: {
  addsort(){
      console.log(this.movie.sort);
      var arr=this.selectedsorts.filter((item)=>{
          return item==this.movie.sort
      })
      if (arr.length==0) {
        if (this.selectedsorts.length<1) {
          this.selectedsorts.push(this.movie.sort)
        }
      }
    },
    handleClose(index){
      this.selectedsorts.splice(index,1)
    }
 },
}
</script>
<style scoped>

</style>