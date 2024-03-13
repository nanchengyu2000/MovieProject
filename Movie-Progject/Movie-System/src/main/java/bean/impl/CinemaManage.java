package bean.impl;

import bean.Cinema;
import entity.*;
import mapper.HistoryMapper;
import mapper.MarkMapper;
import mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaManage implements Cinema {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieExample movieExample;
    @Autowired
    private Movie movie;
    @Override
    public int addmovie(String moviename,String uploadpath) {   //添加电影基础信息
        int result=0;
        movieExample.createCriteria().andMovienameEqualTo(moviename);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        if (movies.size()==0){
            movie.setMoviename(moviename);
            movie.setUploadpath(uploadpath);
            Float aFloat = new Float(0.0);
            movie.setGrade(aFloat);
            movie.setNumber(0);
            result= movieMapper.insert(movie);
        }
        return result;

    }

    @Override
    public int changemoviename(String newvalue, String oldvalue,String uploadpath) {
        int result=0;
        movieExample.createCriteria().andMovienameEqualTo(oldvalue);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        for (Movie movie1 : movies) {
            movie1.setMoviename(newvalue);
            movie1.setUploadpath(uploadpath);
            result= movieMapper.updateByPrimaryKey(movie1);
        }
        return result;
    }

    @Override
    public int updatamovie(Movie movie) {
        int result=0;
        movieExample.createCriteria().andMovienameEqualTo(movie.getMoviename());
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        for (Movie movie1 : movies) {
            movie.setMovieid(movie1.getMovieid());
            movie.setUploadpath(movie1.getUploadpath());
            movie.setGrade(movie1.getGrade());
            movie.setNumber(movie1.getNumber());
            result= movieMapper.updateByPrimaryKey(movie);
        }
        return result;
    }

    @Override
    public int deletemovie(String moviename) {
        movieExample.createCriteria().andMovienameEqualTo(moviename);
        int i = movieMapper.deleteByExample(movieExample);
        movieExample.clear();
        return i;
    }

    @Override
    public int uploadPath(String path, String moviename) {
        int result=0;
        movieExample.createCriteria().andMovienameEqualTo(moviename);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        for (Movie movie : movies) {
            movie.setMoviepath(path);
            result = movieMapper.updateByPrimaryKey(movie);
        }
        return result;
    }

    @Override
    public int uploadImage(String path, String moviename) {
        int result=0;
        movieExample.createCriteria().andMovienameEqualTo(moviename);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        for (Movie movie : movies) {
            movie.setImagepath(path);
            result = movieMapper.updateByPrimaryKey(movie);
        }
        return result;
    }

    @Override
    public List<String> getDir(String path) {
        List<String> list1 = new ArrayList<>();
        File file = new File(path);
        String[] list = file.list();
        for (String s : list) {
            if (!s.equals("image")){
                list1.add(s);
            }
        }
        return list1;
    }

    @Override
    public List<String> getFlies(String path) {
        ArrayList<String> files1 = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            String absolutePath = file1.getAbsolutePath();
            int movie = absolutePath.indexOf("Movie")+5;
            String substring = absolutePath.substring(movie);
            files1.add(substring);
        }
        return files1;
    }

    @Override
    public Movie getMovieinfo(String moviename) {
        Movie movie = null;
        movieExample.createCriteria().andMovienameEqualTo(moviename);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        for (Movie movi : movies) {
            movie=movi;
        }
        return movie;
    }

    @Override
    public List<Movie> getMovieinfobytop(String sort) {
        List<Movie> movies = movieMapper.selectByTop(sort);
        return movies;
    }

    @Override
    public List<Movie> Search(String search) {
        String s='%'+search+'%';
        List<Movie> search1 = movieMapper.Search(s);
        return search1;
    }

    @Override
    public Movie getMovieinfobyid(int movieid) {
        Movie movie = movieMapper.selectByPrimaryKey(movieid);
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        return movies;
    }

    @Override
    public List<Movie> getMoviesbysort(String sort,String moviename) {
        movieExample.createCriteria().andSortEqualTo(sort).andMovienameNotEqualTo(moviename);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return movies;
    }

    @Override
    public List<Movie> getMoviesbysort(String sort) {
        movieExample.createCriteria().andSortEqualTo(sort);
        List<Movie> movies = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return movies;
    }

    @Autowired
    private History history;
    @Autowired
    private HistoryExample historyExample;
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public int sethistory(int userid, int movieid, float history_time) {
        historyExample.createCriteria().andUseridEqualTo(userid).andMovieidEqualTo(movieid);
        List<History> histories = historyMapper.selectByExample(historyExample);
        int result=0;
        historyExample.clear();
        if (histories.size()==0){
            history.setUserid(userid);
            history.setMovieid(movieid);
            history.setHistoryTime(history_time);
            result= historyMapper.insert(history);
        }else {
            for (History history1 : histories) {
                history1.setHistoryTime(history_time);
                result= historyMapper.updateByPrimaryKey(history1);
            }
        }
        return result;
    }

    @Override
    public List<History> getAllhistory(int userid) {
        historyExample.createCriteria().andUseridEqualTo(userid);
        List<History> histories = historyMapper.selectByExample(historyExample);
        historyExample.clear();
        return histories;
    }

    @Override
    public float gethistory(int userid, int movieid) {
        historyExample.createCriteria().andUseridEqualTo(userid).andMovieidEqualTo(movieid);
        List<History> histories = historyMapper.selectByExample(historyExample);
        historyExample.clear();
        float result = 0;
        for (History history1 : histories) {
            result=history1.getHistoryTime();
        }
        return result;
    }

    @Override
    public int delecthistory(int userid, int movieid) {
        historyExample.createCriteria().andUseridEqualTo(userid).andMovieidEqualTo(movieid);
        List<History> histories = historyMapper.selectByExample(historyExample);
        historyExample.clear();
        if (histories.size()!=0) {
            int i = historyMapper.deleteByExample(historyExample);
            return i;
        }
        return 1;
    }

    @Autowired
    private Mark mark;
    @Autowired
    private MarkMapper markMapper;
    @Autowired
    private MarkExample markExample;

    @Override
    public int givescore(int userid,int movieid,float score) {
        //对影视总分的统计
        Movie movie = movieMapper.selectByPrimaryKey(movieid);
        Float grade = movie.getGrade();    //之前评分平均数
        Integer number = movie.getNumber();  //之前评分人数
        int number1=number+1;   //当前评分人数
        float grade1=(grade*number+score)/number1;
        movie.setGrade(grade1);
        movie.setNumber(number1);
        int i = movieMapper.updateByPrimaryKey(movie);
        mark.setMovieid(movieid);
        mark.setUserid(userid);
        mark.setScore(score);
        int insert = markMapper.insert(mark);
        return i+insert;
    }

    @Override
    public float getscore(int userid, int movieid) {
        markExample.createCriteria().andUseridEqualTo(userid).andMovieidEqualTo(movieid);
        List<Mark> marks = markMapper.selectByExample(markExample);
        markExample.clear();
        if (!marks.isEmpty()){
            for (Mark mark1 : marks) {
                return mark1.getScore();
            }
        }
        return 11;
    }
}
