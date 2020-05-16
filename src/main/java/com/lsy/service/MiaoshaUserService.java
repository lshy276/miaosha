package com.lsy.service;

import com.lsy.mapper.MiaoshaUserMapper;
import com.lsy.pojo.MiaoshaUser;
import com.lsy.pojo.MiaoshaUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 李帅豫 on 2020/4/24.
 */
@Service
public class MiaoshaUserService {

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    public MiaoshaUser login(MiaoshaUser miaoshaUser) {
        MiaoshaUser user = miaoshaUserMapper.login(miaoshaUser);
        if(user!=null)
        {
            return user;
        }
        return null;
    }
}
