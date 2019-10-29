package com.feri.openapi.controller;

import com.feri.openapi.dto.UserDto;
import com.feri.openapi.service.UserService;
import com.feri.openapi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:38
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //新增
    @PostMapping("provider/user/save.do")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    //校验昵称
    @GetMapping("provider/user/checkname.do")
    public R checkName(@RequestParam String name){
        return userService.checkName(name);
    }
    //校验手机号
    @GetMapping("provider/user/checkphone.do")
    public R checkPhone(@RequestParam String phone){
        return userService.checkPhone(phone);
    }

}
