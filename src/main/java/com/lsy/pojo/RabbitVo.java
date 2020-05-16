package com.lsy.pojo;

import java.io.Serializable;

/**
 * Created by 李帅豫 on 2020/5/9.
 */
public class RabbitVo implements Serializable{
    private Long miaoshaId;
    private MiaoshaUser miaoshaUser;

    public Long getMiaoshaId() {
        return miaoshaId;
    }

    public void setMiaoshaId(Long miaoshaId) {
        this.miaoshaId = miaoshaId;
    }

    public MiaoshaUser getMiaoshaUser() {
        return miaoshaUser;
    }

    public void setMiaoshaUser(MiaoshaUser miaoshaUser) {
        this.miaoshaUser = miaoshaUser;
    }

    public RabbitVo() {
    }

    public RabbitVo(Long miaoshaId, MiaoshaUser miaoshaUser) {
        this.miaoshaId = miaoshaId;
        this.miaoshaUser = miaoshaUser;
    }

    @Override
    public String toString() {
        return "RabbitVo{" +
                "miaoshaId=" + miaoshaId +
                ", miaoshaUser=" + miaoshaUser +
                '}';
    }
}
