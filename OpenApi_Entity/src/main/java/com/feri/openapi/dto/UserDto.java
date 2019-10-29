package com.feri.openapi.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:16
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private String phone;

}
