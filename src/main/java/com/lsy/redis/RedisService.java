package com.lsy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/4/23.
 */
@Service
public class RedisService {


    @Autowired
    private StringRedisTemplate redisTemplate;


    public void set(RedisKeyPrefix keyPrefix,String key,String value){  //  1


        //  ums:users:    10
        if(null==keyPrefix.getExpireTime()){
            redisTemplate.opsForValue().set(keyPrefix.getPrefix()+key,value);
        }else
            redisTemplate.opsForValue().set(keyPrefix.getPrefix()+key,value,keyPrefix.getExpireTime(), TimeUnit.SECONDS);

    }

    public String get(RedisKeyPrefix keyPrefix,String key){
        return redisTemplate.opsForValue().get(keyPrefix.getPrefix()+key);
    }

    public void delete(RedisKeyPrefix keyPrefix,String key){
        redisTemplate.delete(keyPrefix.getPrefix()+key);
    }


    public boolean contains(RedisKeyPrefix keyPrefix,String key){
        String value = this.get(keyPrefix, key);
        return value!=null;
    }






}
