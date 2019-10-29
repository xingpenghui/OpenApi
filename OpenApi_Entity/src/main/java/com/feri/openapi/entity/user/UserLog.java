package com.feri.openapi.entity.user;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:17
 */
@Data
public class UserLog {
    private BigInteger id;
    private int uid;
    private String info;
    private int type;
    private Date ctime;
}
