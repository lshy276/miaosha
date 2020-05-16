package com.lsy.service;

import com.lsy.controller.MiaoshaController;
import com.lsy.mapper.GoodsMapper;
import com.lsy.mapper.MiaoshaGoodsMapper;
import com.lsy.mapper.MiaoshaOrderMapper;
import com.lsy.mapper.OrderInfoMapper;
import com.lsy.pojo.*;
import com.lsy.redis.RedisKeyPrefix;
import com.lsy.redis.RedisService;
import com.lsy.redis.RedissonConfig;
import com.lsy.vo.GoodsVo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 李帅豫 on 2020/5/1.
 */
@Service
public class MiaoshaOrderService {


    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;

    private final Logger logger =LoggerFactory.getLogger(MiaoshaOrderService.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private MiaoshaGoodsMapper miaoshaGoodsMapper;

    @Autowired
    private RedisService redisService;


    @Autowired
    private RedissonConfig redissonConfig;




    public void updateStock(MiaoshaOrder miaoshaOrder) {

        miaoshaOrder.setId(1l);

        System.out.println("service"+miaoshaOrder);

        miaoshaOrderMapper.add(miaoshaOrder);

    }

    @Transactional(rollbackFor = Exception.class)
    public OrderInfo miaosha(Long miaoshaId, MiaoshaUser miaoshaUser) {

        GoodsVo goodsVo = goodsMapper.findGoodsById(miaoshaId);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsChannel((byte) 1);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getGoodsId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setStatus((byte) 0);


        int insert = orderInfoMapper.insert(orderInfo);

        logger.debug("获取数据"+orderInfo.toString());

        Long orderInfoId = orderInfoMapper.findMaxId();

        System.out.println("orderId"+orderInfo);

        logger.debug(orderInfo+"插入数据最大值");


        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(miaoshaUser.getId());
        miaoshaOrder.setGoodsId(goodsVo.getGoodsId());
        miaoshaOrder.setOrderId(orderInfoId);

        int insert1 = miaoshaOrderMapper.insert(miaoshaOrder);

        if(insert<=0 || insert1<=0){
            throw new RuntimeException("插入订单失败");
        }



        int i  = goodsMapper.stockGoods(miaoshaId);

        if(i<=0){
            throw new RuntimeException("修改库存失败");
        }

        int j = miaoshaGoodsMapper.stockmiaoshaGoods(miaoshaId);

        if(j<=0){
            throw new RuntimeException("修改库存失败");
        }

        redisService.set(RedisKeyPrefix.MIAOSHA,miaoshaUser.getId()+":"+miaoshaId,""+orderInfoId);

        RedissonClient redissonClient = redissonConfig.redissonClient();

        RLock lock = redissonClient.getLock("miaoshaGoods");
        try {
            lock.lock();
            Integer stock = Integer.parseInt(redisService.get(RedisKeyPrefix.STOCK, "" + miaoshaId));
            if(stock>0){
                redisService.set(RedisKeyPrefix.STOCK,""+miaoshaId,""+(stock-1));
            }
            stock = Integer.parseInt(redisService.get(RedisKeyPrefix.STOCK, "" + miaoshaId));

            if(stock<=0){
                MiaoshaController.map.put(miaoshaId,true);
            }
        }
        finally {
            lock.unlock();
        }

        return orderInfo;

    }
}
