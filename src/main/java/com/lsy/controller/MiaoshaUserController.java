package com.lsy.controller;

import com.alibaba.fastjson.JSON;
import com.lsy.pojo.MiaoshaUser;
import com.lsy.redis.RedisKeyPrefix;
import com.lsy.redis.RedisService;
import com.lsy.service.MiaoshaUserService;
import com.lsy.util.Result;
import com.lsy.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by 李帅豫 on 2020/4/24.
 */
@RestController
@RequestMapping("miaoshauser")
public class MiaoshaUserController {
    @Autowired
    private MiaoshaUserService miaoshaService;

    @Autowired
    private RedisService redisService;

    public static final String lsyLoginUUid ="lsyLoginUUid";

    @RequestMapping("login")
    public Result login(@RequestBody MiaoshaUser miaoshaUser, HttpServletResponse response,@CookieValue(value = MiaoshaUserController.lsyLoginUUid,required = false) String token){

        MiaoshaUser user=null;

        System.out.println("token:"+token);

        String uuid=UUID.randomUUID().toString().replace("-","");

        String s = redisService.get(RedisKeyPrefix.PERSON_USERS,token);

        if(s!=null){
            user = JSON.parseObject(s, MiaoshaUser.class);
            System.out.println("从缓存中获取");
        }
        else{
            user = miaoshaService.login(miaoshaUser);
            System.out.println("从数据库中获取");
            if(user!=null){
                redisService.set(RedisKeyPrefix.PERSON_USERS,""+uuid,JSON.toJSONString(user));
            }
        }

        System.out.println("uuid:"+uuid);

        if(user!=null){
            Cookie cookie = new Cookie(MiaoshaUserController.lsyLoginUUid,uuid);
            System.out.println("uuid:"+uuid);
            cookie.setMaxAge(RedisKeyPrefix.PERSON_USERS.getExpireTime().intValue());
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Result(true, StatusCode.OK,"登录成功",user);
        }
        return new Result(false, StatusCode.LOGINERROR,"登录失败");

//        if(s!=null)
//        {
//            user = JSON.parseObject(s, MiaoshaUser.class);
//            System.out.println(""+user);
//            if(user.getId().equals(miaoshaUser.getId())&&user.getPassword().equals(miaoshaUser.getPassword())){
//                redisService.set(RedisKeyPrefix.UUID,"lsyLoginUUid",token);
//                Cookie cookie = new Cookie("lsyLoginUUid",token);
//                cookie.setMaxAge(RedisKeyPrefix.PERSON_USERS.getExpireTime().intValue());
//                cookie.setPath("/");
//                response.addCookie(cookie);
//                return new Result(true, StatusCode.OK,"登录成功",user);
//            }
//        }
//        //MiaoshaUser miaoshaUser1 = JSON.parseObject(s, MiaoshaUser.class);
//        MiaoshaUser user1 = miaoshaService.login(miaoshaUser);
//        if(user1!=null){
//            String s1 = JSON.toJSONString(user1);
//            System.out.println(s1);
//            redisService.set(RedisKeyPrefix.PERSON_USERS,""+miaoshaUser.getId(),s1);
//            redisService.set(RedisKeyPrefix.UUID,"lsyLoginUUid",token);
//            Cookie cookie = new Cookie("lsyLoginUUid",token);
//            cookie.setMaxAge(RedisKeyPrefix.PERSON_USERS.getExpireTime().intValue());
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            return new Result(true, StatusCode.OK,"登录成功",user1);
//        }
    }
}
