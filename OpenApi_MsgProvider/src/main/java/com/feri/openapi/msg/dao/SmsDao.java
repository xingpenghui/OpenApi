package com.feri.openapi.msg.dao;

import com.feri.openapi.msg.entity.Sms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SmsDao {
    //新增
    @Insert("insert into t_sms(recphone,msg,ctime,flag) values(#{recphone},#{msg},now(),#{flag})")
    int insert(Sms sms);
    //查询
    @Select("select * from t_sms order by ctime desc")
    List<Sms> selectAll();
    //查询某个手机号 短信记录
    @Select("select * from t_sms where recphone=#{phone} order by ctime desc")
    List<Sms> queryByPhone(String phone);
}
