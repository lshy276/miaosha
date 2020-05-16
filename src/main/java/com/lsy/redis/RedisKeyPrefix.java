package com.lsy.redis;

/**
 * Created by Administrator on 2020/4/23.
 */
public class RedisKeyPrefix {


    private String prefix;
    private Long expireTime;  // Long long

    /**
     * 统一管理key前缀和有效时间
     * 秒杀模块：seckill:goods:
     */
    public static final RedisKeyPrefix SECKILL_GOODS = new RedisKeyPrefix("seckill:goods:",20l);


    /**
     * 个人中心模块
     *
     */
    public static final RedisKeyPrefix PERSON_USERS = new RedisKeyPrefix("person:users:",300l);


    /**
     *
     *存放库存数量
     *
     * */

    public static final RedisKeyPrefix STOCK = new RedisKeyPrefix("person:stock:",500l);
    /*******
     *
     * 秒杀商品
     */

    public static final RedisKeyPrefix MIAOSHA = new RedisKeyPrefix("person:miaosha:",500l);


    private RedisKeyPrefix(){}
    private RedisKeyPrefix(String prefix, Long expireTime) {
        this.prefix = prefix;
        this.expireTime = expireTime;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
