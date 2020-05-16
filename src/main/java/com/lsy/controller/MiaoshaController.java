package com.lsy.controller;

import com.alibaba.fastjson.JSON;
import com.lsy.pojo.Goods;
import com.lsy.pojo.MiaoshaUser;
import com.lsy.pojo.RabbitVo;
import com.lsy.redis.RedisKeyPrefix;
import com.lsy.redis.RedisService;
import com.lsy.service.GoodsService;
import com.lsy.service.MiaoshaOrderService;
import com.lsy.service.MiaoshaService;
import com.lsy.util.Result;
import com.lsy.util.StatusCode;
import com.lsy.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by 李帅豫 on 2020/4/29.
 */
@RestController
@RequestMapping("miaosha")
public class MiaoshaController implements InitializingBean{

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Logger log = LoggerFactory.getLogger(MiaoshaController.class);


    public static Map<Object,Object> map = new HashMap<Object,Object>();


    @RequestMapping("lijimiaosha")
    public Result lijimiaosha(@RequestParam("miaoshaId") Long miaoshaId, @CookieValue(value = MiaoshaUserController.lsyLoginUUid,required = false) String token){

        MiaoshaUser miaoshaUser=null;

        String s = redisService.get(RedisKeyPrefix.PERSON_USERS, token);

        System.out.println(miaoshaId);

        if(s==null){
            return new Result(false, StatusCode.LOGINERROR,"对不起，请先登录用户");
        }
        miaoshaUser = JSON.parseObject(s,MiaoshaUser.class);

        if(Long.parseLong(redisService.get(RedisKeyPrefix.STOCK,""+miaoshaId))<=0){
            return new Result(false, StatusCode.ERROR,"库存不足");
        }
        Boolean o = (Boolean) map.get(miaoshaId);
        if(o){
            return new Result(false, StatusCode.ERROR,"库存不足");
        }

        String s1 = redisService.get(RedisKeyPrefix.MIAOSHA,miaoshaUser.getId()+":"+miaoshaId);

        if(s1!=null){
            return new Result(false,StatusCode.ERROR,"不能重复下单");
        }
//        if(Long.parseLong(redisService.get(RedisKeyPrefix.STOCK,""+miaoshaOrder.getGoodsId()))<=0){
//            return new Result(false, StatusCode.ERROR,"库存不足");
//        }
//        Boolean o = (Boolean) map.get(miaoshaOrder.getGoodsId());
//        if(o){
//            return new Result(false, StatusCode.ERROR,"库存不足");
//        }
//
//        miaoshaOrder.setUserId(miaoshaUser.getId());
//
//        miaoshaOrderService.updateStock(miaoshaOrder);
//
//        return new Result(true, StatusCode.OK,"商品秒杀成功");

        rabbitTemplate.convertAndSend("miaosha",new RabbitVo(miaoshaId,miaoshaUser));

        return new Result(true,StatusCode.OK,"下单成功");
    }

    @RequestMapping("sendMq")
    public String sendMq(){
        RabbitVo rabbitVo = new RabbitVo();
        rabbitVo.setMiaoshaId(1l);
        rabbitVo.setMiaoshaUser(new MiaoshaUser(6l,"lsy","15236275718"));
        rabbitTemplate.convertAndSend("us",rabbitVo);
        return "成功";
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        List<GoodsVo> list = goodsService.getList();

        System.out.println("进来了");

        for (GoodsVo s: list) {

            System.out.println(s.getGoodsId());
            String t = redisService.get(RedisKeyPrefix.STOCK, "" + s.getGoodsId());
            if(t==null){
                redisService.set(RedisKeyPrefix.STOCK, "" + s.getGoodsId(),""+s.getStockCount());
            }
            if(Long.parseLong(redisService.get(RedisKeyPrefix.STOCK,""+s.getGoodsId()))<=0){
                map.put(s.getGoodsId(),true);
            }else {
                map.put(s.getGoodsId(),false);
            }
        }

//        Set<Map.Entry<Object, Object>> entries = map.entrySet();
//
//        for (Map.Entry<Object, Object> e:
//             entries) {
//            System.out.println(e.getKey()+" "+e.getValue());
//        }

    }

    @RequestMapping("redis")
    public synchronized Result redis(){
        Integer t = Integer.parseInt(redisService.get(RedisKeyPrefix.STOCK, "2"));
        if(t>0){
            t--;
            log.info("库存中的值为："+t);
            redisService.set(RedisKeyPrefix.STOCK, "2",""+t);
        }
        return new Result(true,StatusCode.OK,"成功");
    }

}
