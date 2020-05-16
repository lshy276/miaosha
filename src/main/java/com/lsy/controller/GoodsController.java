package com.lsy.controller;

import com.alibaba.fastjson.JSON;
import com.lsy.pojo.MiaoshaUser;
import com.lsy.redis.RedisKeyPrefix;
import com.lsy.redis.RedisService;
import com.lsy.service.GoodsService;
import com.lsy.util.Result;
import com.lsy.util.StatusCode;
import com.lsy.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by 李帅豫 on 2020/4/26.
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("findAll")
    public Result findAll(@CookieValue(value = MiaoshaUserController.lsyLoginUUid,required = false)String token, Model model, HttpServletResponse response){
        MiaoshaUser user = null;
        String s = redisService.get(RedisKeyPrefix.PERSON_USERS, token);
        if(s==null){
            return new Result(false, StatusCode.LOGINERROR,"对不起，请先登录用户");
        }

        user = JSON.parseObject(s,MiaoshaUser.class);

        redisService.set(RedisKeyPrefix.PERSON_USERS,token,JSON.toJSONString(user));

        Cookie cookie = new Cookie(MiaoshaUserController.lsyLoginUUid,token);
        System.out.println("uuid:"+token);
        cookie.setMaxAge(RedisKeyPrefix.PERSON_USERS.getExpireTime().intValue());
        cookie.setPath("/");
        response.addCookie(cookie);


        List<GoodsVo> goodsVoList = goodsService.getList();

        System.out.println("开始时间："+goodsVoList.get(0).getStartDate().toLocaleString());
        System.out.println("结束时间："+goodsVoList.get(0).getEndDate().toLocaleString());

        model.addAttribute("user",user);

        model.addAttribute("goodsList",goodsVoList);

        return new Result(true, StatusCode.OK,"查询成功",model);
    }

    @RequestMapping("getList")
    public List<GoodsVo> getList(){
        return goodsService.getList();
    }

    @RequestMapping("findGoodsById")
    public Result findGoodsById(Long id, @CookieValue(value = MiaoshaUserController.lsyLoginUUid,required = false)String token, Model model, HttpServletResponse response){
        String s = redisService.get(RedisKeyPrefix.PERSON_USERS, token);
        if(s!=null){
            MiaoshaUser miaoshaUser = JSON.parseObject(s, MiaoshaUser.class);
            model.addAttribute("miaoshaUser",miaoshaUser);
            GoodsVo goodsVo = goodsService.findGoodsById(id);
            System.out.println("开始时间："+goodsVo.getStartDate().toLocaleString());
            System.out.println("结束时间："+goodsVo.getEndDate().toLocaleString());
            model.addAttribute("goodVo",goodsVo);
            model.addAttribute("currenttime",new Date().getTime());

            redisService.set(RedisKeyPrefix.PERSON_USERS,token,JSON.toJSONString(miaoshaUser));

            Cookie cookie = new Cookie(MiaoshaUserController.lsyLoginUUid,token);
            System.out.println("uuid:"+token);
            cookie.setMaxAge(RedisKeyPrefix.PERSON_USERS.getExpireTime().intValue());
            cookie.setPath("/");
            response.addCookie(cookie);

            return new Result(true, StatusCode.OK,"查询成功",model);
        }
        return null;
    }


}
