package com.feri.openapi.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.feri.openapi.config.RedisKeyConfig;
import com.feri.openapi.dto.SmsCodeDto;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.msg.dao.SmsDao;
import com.feri.openapi.msg.entity.Sms;
import com.feri.openapi.msg.model.SmsBean;
import com.feri.openapi.msg.service.SmsService;
import com.feri.openapi.msg.util.AliSmsUtil;
import com.feri.openapi.myenum.SmsTemCode;
import com.feri.openapi.redis.RedissionUtil;
import com.feri.openapi.util.RUtil;
import com.feri.openapi.util.RandomNumUtil;
import com.feri.openapi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:27
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsDao smsDao;
    @Override
    public R sendMsg(SmsDto smsDto) {
        //验证码如果没失效，就重新发送 不要重新生成

        Sms sms=new Sms();
        sms.setFlag(1);
        sms.setRecphone(smsDto.getReceivePhone());
        //1、调用API
        if(smsDto.getType()==1){
            //短信验证码
            int code=0;
            //验证码未失效
            if(RedissionUtil.checkKey(RedisKeyConfig.SMS_CODE+smsDto.getReceivePhone())){
                code=Integer.parseInt(RedissionUtil.getStr(RedisKeyConfig.SMS_CODE+smsDto.getReceivePhone()));
                sms.setMsg("重新发送短信验证码："+code);
            }else {
                //1、生成验证码
                code= RandomNumUtil.createNum(6);
                sms.setMsg("发送短信验证码："+code);
            }
            //发送短信---思考---RabbitMQ  异步消息
            String res=AliSmsUtil.sendSms(SmsTemCode.YanZhengMa.getCode(),smsDto.getReceivePhone(),"{\"code\":\""+code+"\"}");
            //2、验证验证码是否发送
            if(res!=null){
                //3、验证是否发送成功
                SmsBean bean= JSON.parseObject(res,SmsBean.class);
                if(Objects.equals(bean.getCode(),"OK")){
                    //发送成功
                    sms.setFlag(2);
                    //存储验证码---Redis  有效期 10分钟
                    RedissionUtil.setStr(RedisKeyConfig.SMS_CODE+smsDto.getReceivePhone(),code+"",600);
                }else {
                    sms.setFlag(3);
                }
            }
        }else if(smsDto.getType()==2){
            //推广信息
        }
        //记录数据到数据库
        smsDao.insert(sms);
        return RUtil.setOK();
    }

    @Override
    public R querySms(String phone) {
        return RUtil.setOK(smsDao.queryByPhone(phone));
    }

    @Override
    public R queryPage(int page, int size) {
        return null;
    }

    @Override
    public R checkCode(SmsCodeDto codeDto) {
       //1、验证 Redis额验证码是否失效
        if(RedissionUtil.checkKey(RedisKeyConfig.SMS_CODE+codeDto.getPhone())){
            //2、获取之前的验证码  Redis
            String code=RedissionUtil.getStr(RedisKeyConfig.SMS_CODE+codeDto.getPhone());
            //3、验证码比对
            if(Objects.equals(code,codeDto.getCode())){
                //4、验证码正确
                return RUtil.setOK();
            }else {
                return RUtil.setERROR("验证码输入错误，请检查");
            }
        }else {
            return  RUtil.setERROR("验证码失效，请重新获取");
        }
    }
}
