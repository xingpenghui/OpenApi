package com.feri.openapi.web;

import com.feri.openapi.dto.UserDto;
import com.feri.openapi.service.UserService;
import com.feri.openapi.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:51
 */
@RestController
@Api(value = "用户相关操作",tags = "用户相关操作")
public class UserController {
    @Autowired
    private UserService userService;
    //新增
    @PostMapping("api/user/save.do")
    @ApiOperation(value = "实现用户的注册",notes = "实现用户的注册")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    //校验昵称
    @ApiOperation(value = "校验昵称",notes = "校验昵称")
    @GetMapping("api/user/checkname.do")
    public R checkName(@RequestParam String name){
        return userService.checkName(name);
    }
    //校验手机号
    @ApiOperation(value = "校验手机号",notes = "校验手机号")
    @GetMapping("api/user/checkphone.do")
    public R checkPhone(@RequestParam String phone){
        return userService.checkPhone(phone);
    }
}
