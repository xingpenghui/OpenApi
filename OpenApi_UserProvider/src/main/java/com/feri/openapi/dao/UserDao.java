package com.feri.openapi.dao;

import com.feri.openapi.dto.UserDto;
import com.feri.openapi.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Insert("insert into t_user(name,pass,phone) values(#{name},#{pass},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);
    @Select("select * from t_user where name=#{n} or phone=#{n}")
    @ResultType(User.class)
    User selectOne(String n);

}
