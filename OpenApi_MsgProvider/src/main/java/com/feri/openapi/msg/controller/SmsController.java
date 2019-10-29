package com.feri.openapi.msg.controller;

import com.feri.openapi.dto.SmsCodeDto;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.msg.service.SmsService;
import com.feri.openapi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:46
 */
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    //发送
    @PostMapping("provider/sms/sendcode.do")
    public R sendSms(@RequestBody SmsDto dto){
        return smsService.sendMsg(dto);
    }
    //校验
    @GetMapping("provider/sms/checkcode.do")
    public R checkCode(@RequestBody SmsCodeDto dto){
        return smsService.checkCode(dto);
    }

}
