package com.happyhome.mapper;

import com.happyhome.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findByUserId(String userid);

    @Insert("insert into user(id, pass, name, address, number, role) values (#{id}, #{pass}, #{name} , #{address}, #{number}, #{role})")
    int saveUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(User user);

    @Update("update user set id = #{id}, name = #{name}, address = #{address}, number = #{number} where no = ${no}")
    int UpdateUser(User user);
}
