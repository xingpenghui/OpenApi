package com.feri.openapi.service;

import com.feri.openapi.dto.UserDto;
import com.feri.openapi.vo.R;

public interface UserService {
    R save(UserDto userDto);
    R checkPhone(String phone);
    R checkName(String name);

}
