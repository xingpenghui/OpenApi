package com.feri.openapi.service;

import com.feri.openapi.dto.UserDto;
import com.feri.openapi.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("userProvider")
public interface UserService {
    @PostMapping("provider/user/save.do")
    R save(@RequestBody UserDto userDto);
    //校验昵称
    @GetMapping("provider/user/checkname.do")
    R checkName(@RequestParam String name);
    //校验手机号
    @GetMapping("provider/user/checkphone.do")
    R checkPhone(@RequestParam String phone);
}
