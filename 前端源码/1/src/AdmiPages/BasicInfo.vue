<template>
  <div class="box">
    <div class="fileList">
      <el-card>
        <div slot="header" class="clearfix">
          <span>电影基本信息</span>
          <el-button style="float: right; padding: 3px 0;width:50px;height:30px" type="primary">修改<i class="el-icon-edit"></i></el-button>
        </div>
        <el-form :model="movie">
          <el-form-item label="电影名称">
            <el-input style="width: 200px;" v-show="isedior" v-model="movie.moviename" autocomplete="off"></el-input>
            <span>{{movie.moviename}}</span>
          </el-form-item>
          <el-form-item label="电影地区" >
            <el-input style="width: 200px;" v-show="isedior" v-model="movie.moviearea" autocomplete="off"></el-input>
          <span>{{movie.moviearea}}</span>
          </el-form-item>
          <el-form-item label="电影类型" >
            <span v-show="isedior" style="margin-right:10px">
              <el-tag size="small" style="margin-right:5px" v-for="(sor,index) in selectedsorts" :key="index" closable @close="handleClose(index)">{{sor}}</el-tag>
            </span>
            <el-select v-show="isedior" v-model="movie.sort" @change="addsort" placeholder="请选择电影类型">
              <el-option v-for="(sort,index) in sorts" :key="index" :label="sort" :value="sort"></el-option>
            </el-select>
          <span>{{movie.sort}}</span>
          </el-form-item>
          <el-form-item label="电影时长" >
            <el-time-picker
                v-show="isedior"
                v-model="movie.duration"
                :picker-options="{
                  selectableRange: '00:00:00 - 3:00:00'
                }"
                placeholder="任意时间点">
            </el-time-picker>
          <span>{{movie.duration}}</span>
          </el-form-item>
          <el-form-item label="中国上架时间">
            <el-date-picker v-show="isedior" type="date" placeholder="选择日期" v-model="movie.dateissued" style="width: 200px;"></el-date-picker>
          <span>{{movie.dateissued}}</span>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>
<script>
import {Message,Loading} from 'element-ui'
export default {
  data() {
    return {
      moviename:"",
      movie:{},
      isedior:false,
      loading:""
    }
  },
  mounted() {
    this.loading=Loading.service({
          lock: true,
          text: '加载中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
    this.moviename=this.$route.query.moviename
    this.getinfo()
  },
  methods: {
    getinfo(){
      this.$axios({
        url:"/movie/getMovieInfo",
        params:{
          moviename:this.moviename
        }
      }).then((res)=>{
        if (res.data) {
          this.movie=res.data
        }
        this.loading.close()
      })
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
.el-card {
  width: 60%;
  height: 400px;
  margin-left: 20%;
}
 .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

</style>