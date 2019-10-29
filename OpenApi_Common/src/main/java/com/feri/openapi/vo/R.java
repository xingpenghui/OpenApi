package com.feri.openapi.vo;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:28
 */
@Data
public class R {
    private int code;
    private String msg;
    private Object data;
}
