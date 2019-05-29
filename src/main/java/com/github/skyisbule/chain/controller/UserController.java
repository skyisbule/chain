package com.github.skyisbule.chain.controller;

import com.github.skyisbule.chain.common.BaseHttpResponse;
import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.exception.GlobalException;
import com.github.skyisbule.chain.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("注册")
    @RequestMapping("/register")
    public BaseHttpResponse<User> doRegister(User user) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doRegister(user));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("登录")
    @RequestMapping("/login")
    public BaseHttpResponse<User> doLogin(String userName,String passowrd) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doLogin(userName,passowrd));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }


}
