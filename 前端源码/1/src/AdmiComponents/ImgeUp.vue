<template>
  <div>
    <el-upload
  ref="upload"
  :action="baseURL+'User/changeheader'"
  list-type="picture-card"
  :on-preview="handlePictureCardPreview"
  :on-remove="handleRemove"
  accept=".png,.jpeg,.jpg,.mp4"
  :limit="1"
  name="photo"
  :file-list="fileList"
  :data="other"
  :auto-upload="false"
  >
  <i class="el-icon-plus"></i>
</el-upload>
<el-dialog :visible.sync="dialogVisible">
  <img width="100%" :src="dialogImageUrl">
</el-dialog>
  </div>
</template>
<script>
 export default {
    data() {
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        fileList:[],
        other:{
          name:sessionStorage.getItem("usename")
        },
        baseURL:this.$axios.defaults.baseURL
      };
    },
    mounted(){
      this.$store.commit("changeheader",this.submitUpload)
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