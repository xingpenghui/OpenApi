package com.feri.openapi.dao;

import com.feri.openapi.entity.user.UserLog;
import org.apache.ibatis.annotations.Insert;

public interface UserLogDao {
    @Insert("insert into t_userlog(uid,info,ctime,type) values(#{uid},#{info},now(),#{type})")
    int insert(UserLog log);
}
