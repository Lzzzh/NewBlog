package com.blog.mapper;

import com.blog.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //查询
    @Select(value = "select userId, userPassword from user where userId = #{userId} and userPassword = #{userPassword}")
    @Results({@Result(property = "userId", column = "userId"),
            @Result(property = "userPassword", column = "userPassword")})
    User findUserByName(@Param("userId") String userId, @Param("userPassword") String userPassword);

    //注册插入数据
    @Insert("insert into user(userId, userName, userPassword) values(#{userId}, #{userName}, #{userPassword})")
    //加入该注解可以保存对象后，查看对象插入id
    //@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void regist(User user);

    @Select("select userId from user where userId = #{userId} and userPassword = #{userPassword}")
    String login(User user);
}
