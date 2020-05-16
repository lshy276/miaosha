package com.lsy.service;

import com.lsy.mapper.UsersMapper;
import com.lsy.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 李帅豫 on 2020/4/22.
 */
@Service
public class UsersService {

    /**
     *
     */
    @Autowired
    private UsersMapper usersMapper;

    public Users login(Users users) {
        Users user = usersMapper.login(users);
        System.out.println("查询出来的对象为："+user);
        if(user!=null){
            return user;
        }
        return null;
    }
}
