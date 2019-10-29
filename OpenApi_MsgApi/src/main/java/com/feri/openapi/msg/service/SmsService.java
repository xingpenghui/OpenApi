package com.feri.openapi.msg.service;

import com.feri.openapi.dto.SmsCodeDto;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("msgProvider")
public interface SmsService {
    //发送
    @PostMapping("provider/sms/sendcode.do")
    R sendSms(@RequestBody SmsDto dto);
    //校验
    @GetMapping("provider/sms/checkcode.do")
    R checkCode(@RequestBody SmsCodeDto dto);
}
