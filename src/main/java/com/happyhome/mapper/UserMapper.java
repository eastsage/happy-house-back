package com.happyhome.mapper;

import com.happyhome.model.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findByUserId(String userid);

    @Insert("insert into user(username, password, roles) values (#{username}, #{password}, #{roles})")
    int saveUser(User user);

    @Delete("delete from user where username = #{username}")
    int deleteUser(User user);

    @Update("update user set username = #{username} where username = #{username}")
    int UpdateUser(User user);

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
}
