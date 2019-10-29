package com.feri.openapi.config;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 14:32
 */
public class RedisKeyConfig {
    //短信验证码
    public static final String SMS_CODE="sms:code:";//后面跟上手机号  10分钟有效

    //短信发送频率
    public static final String SMS_MIN="sms:pl:min:";//后面跟上手机号 1分钟
    public static final String SMS_HOU="sms:pl:hou:";//后面跟上手机号 1小时 5次
    public static final String SMS_DAY="sms:pl:day:";//后面跟上手机号 1天  10次

}
