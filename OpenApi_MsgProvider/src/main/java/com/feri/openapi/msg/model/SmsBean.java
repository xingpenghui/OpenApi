package com.feri.openapi.msg.model;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:25
 */
@Data
public class SmsBean {
    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;
}
