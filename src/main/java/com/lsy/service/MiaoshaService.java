package com.lsy.service;

import com.lsy.controller.MiaoshaController;
import com.lsy.pojo.MiaoshaUser;
import com.lsy.pojo.OrderInfo;
import com.lsy.redis.RedisKeyPrefix;
import com.lsy.redis.RedisService;
import com.lsy.util.Result;
import com.lsy.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李帅豫 on 2020/5/6.
 */
@Service
public class MiaoshaService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;


    public Result miaosha(Long miaoshaId, MiaoshaUser miaoshaUser) {
        if(Long.parseLong(redisService.get(RedisKeyPrefix.STOCK,""+miaoshaId))<=0){
            return new Result(false, StatusCode.ERROR,"库存不足");
        }
        Boolean o = (Boolean) MiaoshaController.map.get(miaoshaId);
        if(o){
            return new Result(false, StatusCode.ERROR,"库存不足");
        }

        String s = redisService.get(RedisKeyPrefix.MIAOSHA,miaoshaUser.getId()+":"+miaoshaId);

        if(s!=null){
            return new Result(false,StatusCode.ERROR,"不能重复下单");
        }

        OrderInfo orderInfo = miaoshaOrderService.miaosha(miaoshaId,miaoshaUser);

        return new Result(true,StatusCode.OK,"成功",orderInfo);
    }
}
