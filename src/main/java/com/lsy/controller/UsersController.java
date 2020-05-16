package com.lsy.controller;

import com.lsy.pojo.Users;
import com.lsy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李帅豫 on 2020/4/22.
 */
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("login")
    public Users login(Users users){
        System.out.println("对象值为："+users);
        return usersService.login(users);
    }
}
