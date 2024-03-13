package mapper;

import entity.Movie;
import entity.MovieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface MovieMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int countByExample(MovieExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int deleteByExample(MovieExample example);

    List<Movie> selectByTop(String sort);
    List<Movie> Search(String search);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int deleteByPrimaryKey(Integer movieid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int insert(Movie record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int insertSelective(Movie record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    List<Movie> selectByExample(MovieExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    Movie selectByPrimaryKey(Integer movieid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int updateByExampleSelective(@Param("record") Movie record, @Param("example") MovieExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int updateByExample(@Param("record") Movie record, @Param("example") MovieExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int updateByPrimaryKeySelective(Movie record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated Wed Dec 14 13:27:57 GMT+08:00 2022
     */
    int updateByPrimaryKey(Movie record);
}