package com.feri.openapi.msg.service;

import com.feri.openapi.dto.SmsCodeDto;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.vo.R;

public interface SmsService {
    //发送短信
    R sendMsg(SmsDto smsDto);
    //查询某个手机号的短信记录
    R querySms(String phone);
    //查询所有的短信信息 分页
    R queryPage(int page,int size);
    //校验验证码
    R checkCode(SmsCodeDto codeDto);

}
