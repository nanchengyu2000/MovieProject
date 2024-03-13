package bean;

import entity.History;
import entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Cinema {
    public int addmovie(String moviename,String uploadpath);
    public int changemoviename(String newvalue,String oldvalue,String uploadpath);
    public int updatamovie(Movie movie);
    public int deletemovie(String moviename);
    public int uploadPath(String path,String moviename);
    public int uploadImage(String path,String moviename);
    public List<String> getDir(String path);
    public List<String> getFlies(String path);
    public Movie getMovieinfo(String moviename);
    public List<Movie> getMovieinfobytop(String sort);
    public List<Movie> Search(String search);
    public Movie getMovieinfobyid(int movieid);
    public List<Movie> getAllMovies();
    public List<Movie> getMoviesbysort(String sort,String moviename);
    public List<Movie> getMoviesbysort(String sort);
    public int sethistory(int userid,int movieid,float history_time);
    public List<History> getAllhistory(int userid);
    public float gethistory(int userid,int movieid);
    public int delecthistory(int userid,int movieid);
    public int givescore(int userid,int movieid,float score);
    public float getscore(int userid,int movieid);
}
