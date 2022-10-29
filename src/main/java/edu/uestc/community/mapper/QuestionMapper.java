package edu.uestc.community.mapper;

import edu.uestc.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into QUESTION(title,description,gmt_create,gmt_modified,creator,tag) " +
            "values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from QUESTION limit #{page},#{size}")
    List<Question> list(@Param(value = "page") Integer page, @Param("size") Integer size);

    @Select("select count(1) from question ")
    Integer count();
}
