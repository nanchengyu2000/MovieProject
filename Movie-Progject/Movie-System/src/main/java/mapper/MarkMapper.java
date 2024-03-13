package mapper;

import entity.Mark;
import entity.MarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface MarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int countByExample(MarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int deleteByExample(MarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int insert(Mark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int insertSelective(Mark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    List<Mark> selectByExample(MarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int updateByExampleSelective(@Param("record") Mark record, @Param("example") MarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbggenerated Wed Dec 14 13:24:47 GMT+08:00 2022
     */
    int updateByExample(@Param("record") Mark record, @Param("example") MarkExample example);
}