package com.feri.openapi.entity.user;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:17
 */
@Data
public class User {
    private Integer id;
    private String  name;
    private String  pass;
    private String  phone;
}
