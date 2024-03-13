<template>
  <div>
    <!-- action="https://vyke0kcd.beesnat.com/Movie-System/movie/uploadImage" -->
    <!-- http://localhost:8084/System/movie/uploadImage -->
    <el-upload
  :action="SystemPath+'movie/uploadImage'"
  ref="upload"
  list-type="picture-card"
  :on-preview="handlePictureCardPreview"
  :on-remove="handleRemove"
  accept=".png,.jpeg,.jpg"
  :limit="1"
  :data="other"
  name="img"
  :file-list="fileList"
  :auto-upload="true"
  >
  <i class="el-icon-plus"></i>
</el-upload>
<p style="margin-left:60px">上传电影展示图片</p>
<el-dialog :visible.sync="dialogVisible">
  <img width="100%" :src="dialogImageUrl" alt="">
</el-dialog>
  </div>
</template>
<script>
 export default {
    data() {
      return {
        fileList:[],
        dialogImageUrl: '',
        dialogVisible: false,
        other:{
          moviename:sessionStorage.getItem("moviename"),
        },
        SystemPath:this.$SystemPath
      };
    },
    methods: {
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }
    }
  }
</script>
<style scoped>

</style>