package edu.uestc.community.mapper;

import edu.uestc.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into Users (name, account_id, token, gmt_create, gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User uesr);

    @Select("select * from Users where token = #{token}")
    User finByToken(String token);

    @Select("select * from Users where id = #{id}")
    User findById(Integer id);
}
