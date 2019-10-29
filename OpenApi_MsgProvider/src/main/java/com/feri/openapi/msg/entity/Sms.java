package com.feri.openapi.msg.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:05
 */
@Data
public class Sms {
    private Integer id;
    private String recphone;
    private String msg;
    private Date ctime;
    private int flag;//1 未发送 2发送成功 3发送失败


}
