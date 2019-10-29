package com.feri.openapi.msg.web;

import com.feri.openapi.dto.SmsCodeDto;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.msg.service.SmsService;
import com.feri.openapi.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 15:03
 */
@RestController
@Api(value = "实现短信的相关操作",tags = "实现短信的相关操作")
public class SmsController {
    @Autowired //只能根据类型注入
//    @Qualifier("")
//    @Resource //可以根据类型或id注入
    private SmsService smsService;

    //发送
    @ApiOperation(value = "发送短信验证码",notes = "发送短信验证码")
    @PostMapping("api/sms/sendcode.do")
    public R sendSms(@RequestBody SmsDto dto){
        return smsService.sendSms(dto);
    }
    //校验
    @ApiOperation(value = "校验短信验证码",notes = "校验短信验证码")
    @GetMapping("api/sms/checkcode.do")
    public R checkCode(@RequestBody SmsCodeDto dto){
        return smsService.checkCode(dto);
    }
}
