package com.feri.openapi.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:07
 */
@Data
public class SmsDto {
    private String receivePhone;//接收手机号
    private int type; //模板类型 1 验证码 2推广信息 3 找回密码 4 异地登录
    private String msg;//消息
}
