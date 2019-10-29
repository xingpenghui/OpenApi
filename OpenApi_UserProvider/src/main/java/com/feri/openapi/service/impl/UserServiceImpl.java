package com.feri.openapi.service.impl;

import com.feri.openapi.dao.UserDao;
import com.feri.openapi.dto.UserDto;
import com.feri.openapi.dao.UserLogDao;
import com.feri.openapi.entity.user.User;
import com.feri.openapi.entity.user.UserLog;
import com.feri.openapi.myenum.UserLogType;
import com.feri.openapi.service.UserService;
import com.feri.openapi.util.RUtil;
import com.feri.openapi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:31
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLogDao logDao;
    @Override
    public R save(UserDto userDto) {
        //非空校验
        if(userDto!=null && userDto.getUsername() !=null && userDto.getPassword()!=null){
            //实现用户的新增
            User user=new User();
            user.setName(userDto.getUsername());
            user.setPass(userDto.getPassword());
            user.setPhone(userDto.getPhone());
            //校验是否新增成功
            if(userDao.insert(user)>0){
                //记录日志
                UserLog log=new UserLog();
                log.setInfo("用户实现了注册");
                log.setUid(user.getId());
                log.setType(UserLogType.Register.getValue());
                logDao.insert(log);
                return RUtil.setOK();
            }
        }
        return RUtil.setERROR();
    }

    @Override
    public R checkPhone(String phone) {
        User user=userDao.selectOne(phone);
        if(user!=null){
            //手机号重复
            return RUtil.setERROR();
        }else {
            return RUtil.setOK();
        }
    }

    @Override
    public R checkName(String name) {
        User user=userDao.selectOne(name);
        if(user!=null){
            //名称重复
            return RUtil.setERROR();
        }else {
            return RUtil.setOK();
        }
    }
}
