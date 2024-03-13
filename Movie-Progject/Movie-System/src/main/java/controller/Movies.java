package controller;

import bean.Cinema;
import bean.UserManage;
import entity.Movie;
import entity.User;
import org.springframework.web.bind.annotation.*;
import entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import util.SpliceUpload;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class Movies {
    @Autowired
    private Cinema cinema;
    @Autowired
    private UserManage userManage;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/create")
    /*创建用户文件夹和创建影视文件夹，并插入影视记录*/
    public Boolean create(String filename,String username){
        String basepath="D:\\Movie"+ File.separator+username;
        boolean result=false;
        File file = new File(basepath);
        boolean exists = file.exists();
        if (!exists){
            file.mkdir();
        }
        String moviepath=basepath+File.separator+filename; //创建影视文件
        File file1 = new File(moviepath);
        boolean exists1 = file1.exists();
        if (!exists1){
            result = file1.mkdir();
            if (result){    //将影视文件的文件名作为影视的名称
                String uploadpath=File.separator+username+File.separator+filename;
                int addmovie = cinema.addmovie(filename,uploadpath);
                if (addmovie==1){
                    result=true;
                }else {
                    result=false;
                }
            }
        }
        return result;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/changedirname")
    /*更改文件夹(影视)名称夹*/
    public Boolean changedirname(String username,String newname,String oldname){
        String oldpath="D:\\Movie"+File.separator+username+File.separator+oldname;
        String newpath="D:\\Movie"+File.separator+username+File.separator+newname;
        File file = new File(oldpath);
        boolean b = file.renameTo(new File(newpath));
        //同时需要去数据更改向对应的影视名称
        if (b){
            String uploadpath=File.separator+username+File.separator+newname;
            int changemoviename = cinema.changemoviename(newname,oldname,uploadpath);
            System.out.println(changemoviename);
            if (changemoviename!=1){
                b=false;
            }
        }
        return b;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/rmdir")
    /*删除文件夹*/
    public int rmdir(String username,String filename){
        String filepath="D:\\Movie"+File.separator+username+File.separator+filename;
        File file = new File(filepath);
        boolean delete = delete(file);
        if (delete||!file.exists()){//同时要去数据库中删除对应的影视记录
            Movie movieinfo = cinema.getMovieinfo(filename);
            User user = userManage.getUser(username);
            int delecthistory = cinema.delecthistory(user.getUserid(), movieinfo.getMovieid());
            int deletemovie = cinema.deletemovie(filename);
            if (deletemovie!=1||delecthistory!=1){
                return 0;   //表示文件删除失败
            }else {
                return 1;   //表示文件删除成功
            }
        }
         return 2;  //表示正在切片不能删除
    }
    private static boolean delete(File file){
        boolean result=false;
        if (!file.isDirectory()){
            file.delete();
        }else {
            File[] files = file.listFiles();
            if (files.length==0){  //空文件夹
                file.delete();
            }else {
                for (File file1 : files) {
                    delete(file1);
                }
                result= file.delete();
            }
        }
        return result;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getdir")
    /*根据路径获取影视文件夹*/
    public List<String> getdir(String path){
        String dirpath="D:\\Movie"+path;
        List<String> dir = cinema.getDir(dirpath);
        return dir;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getFlies")
    /*获取文件文件或文件夹名称*/
    public List<String> getFlies(String path){
        String relativepath="D:\\Movie"+path;
        List<String> flies = cinema.getFlies(relativepath);
        return flies;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getAllMovies")
    /*获取所有的影视*/
    public List<Movie> getAllMovies(){
        List<Movie> allMovies = cinema.getAllMovies();
        return allMovies;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMoviesbysort")
    /*获取所有相同类型的影视*/
    public List<Movie> getMoviesbysort(String sort,String moviename){
        List<Movie> allMovies = cinema.getMoviesbysort(sort,moviename);
        return allMovies;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getbysort")
    /*获取所有相同类型的影视*/
    public List<Movie> getbysort(String sort){
        List<Movie> allMovies = cinema.getMoviesbysort(sort);
        return allMovies;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMovieinfobytop")
    public List<Movie> getMovieinfobytop(String sort){
        List<Movie> movieinfobytop = cinema.getMovieinfobytop(sort);
        return movieinfobytop;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/Search")
    public List<Movie> Search(String search){
        List<Movie> search1 = cinema.Search(search);
        return search1;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMovieInfo")
    /*获取单个影视的信息*/
    public Movie getMovieInfo(String moviename){
        Movie movieinfo = cinema.getMovieinfo(moviename);
        return movieinfo;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMovieInfobyid")
    /*获取单个影视的信息*/
    public Movie getMovieInfobyid(int movieid){
        Movie movieinfo = cinema.getMovieinfobyid(movieid);
        return movieinfo;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/addmovie")
    /*添加影视的基本信息*/
    public int addmovie(@RequestBody Movie movie){   //要更改一下，插入改为更新
        System.out.println(movie.getMoviename()+""+movie.getMoviearea());
        int addmovie = cinema.updatamovie(movie);
        return addmovie;
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("/uploadPath")
    /*上传视频*/
    public int uploadPath(MultipartFile movie, String moviename, String username, String filename,int count, int current) throws IOException {
        int i =0;
        String basepath="D:\\Movie"+File.separator+username+File.separator+filename;
        String filepath=basepath+File.separator+"影视";
        String filepath2=basepath+File.separator+"影视"+File.separator+"切片";
        File file = new File(filepath);
        if (!file.exists()){
            file.mkdir();
            String actors=basepath+File.separator+"导演集合";
            String stagephoto=basepath+File.separator+"剧照";
            String stagephot1=basepath+File.separator+"基础信息";
            String preview=basepath+File.separator+"预告片";
            File file1 = new File(actors);
            File file2 = new File(stagephoto);
            File file4 = new File(stagephot1);
            File file3 = new File(preview);
            File file5 = new File(filepath2);
            file1.mkdir();
            file2.mkdir();
            file3.mkdir();
            file4.mkdir();
            file5.mkdirs();
        }
        String s = Long.toString(new Date().getTime());
        String originalFilename ="Source.mp4";
        String Srouce=filepath+File.separator+originalFilename;
        Boolean collector = SpliceUpload.Collector(movie, count, current, filepath2 + File.separator, filepath + File.separator + originalFilename);
        if (collector) {
            File file1 = new File(filepath2);
            boolean delete = delete(file1);  //删除上传切片文件
            int il = originalFilename.indexOf(".");
            String name = originalFilename.substring(0, il);
            String relativepath = filepath + File.separator + name + ".m3u8";
            String databaseurl = File.separator + username + File.separator + filename + File.separator + "影视" + File.separator + name + ".m3u8";
            Runtime runtime = Runtime.getRuntime();
            String commint = "ffmpeg -i " + Srouce + " -threads 50 -codec: copy -start_number 0 -hls_time 10 -hls_list_size 0 -f hls " + relativepath;
            runtime.exec(commint);
            i = cinema.uploadPath(databaseurl, moviename);
        }
        return i;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/uploadImage")
    /*上传影视展示图片*/
    public int uploadImage(MultipartFile img,String moviename) throws IOException {
        String originalFilename = img.getOriginalFilename();
        String dirpath="D:\\Movie"+File.separator+"image";
        File file = new File(dirpath);
        if (!file.exists()){
            file.mkdir();
        }
        String filepath="D:\\Movie"+File.separator+"image"+File.separator+originalFilename;
        String relativepath=File.separator+"image"+File.separator+originalFilename;
        img.transferTo(new File(filepath));
        int i = cinema.uploadImage(relativepath, moviename);
        return i;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/uploadStage")
    /*上传剧照*/
    public String uploadStage(MultipartFile Stage,String path){
        String originalFilename = Stage.getOriginalFilename();
        String relpath="D:\\Movie"+File.separator+path+File.separator+originalFilename;
        String result = "true";
        File file = new File(relpath);
        if (!file.exists()) {
            try {
                Stage.transferTo(file);
            } catch (IOException e) {
                result = "错误信息:" + e.toString();
            }
            return result;
        }
        return result;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/sethistory")
    public int sethistory(int userid, int movieid, float historytime){
        int sethistory = cinema.sethistory(userid, movieid, historytime);
        return sethistory;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getAllhistory")
    public List<History> getAllhistory(int userid){
        List<History> allhistory = cinema.getAllhistory(userid);
        return allhistory;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("/gethistory")
    public float gethistory(int userid,int movieid){
        float gethistory = cinema.gethistory(userid, movieid);
        return gethistory;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/givescore")
    public int givescore(int userid,int movieid,float score){
        int givescore = cinema.givescore(userid, movieid, score);
        return givescore;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getscore")
    public float getscore(int userid,int movieid){
        float getscore = cinema.getscore(userid, movieid);
        return getscore;
    }

}
