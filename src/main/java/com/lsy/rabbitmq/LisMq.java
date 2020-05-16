package com.lsy.rabbitmq;

import com.lsy.pojo.Goods;
import com.lsy.pojo.RabbitVo;
import com.lsy.service.MiaoshaService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 李帅豫 on 2020/5/9.
 */
@Service
public class LisMq {

    @Autowired
    private MiaoshaService miaoshaService;

    @RabbitListener(queues = "miaosha")
    public void jieshou(RabbitVo rabbitVo){

        System.out.println("接受到的消息"+rabbitVo);

        miaoshaService.miaosha(rabbitVo.getMiaoshaId(),rabbitVo.getMiaoshaUser());

    }
}
