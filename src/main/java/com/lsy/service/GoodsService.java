package com.lsy.service;

import com.lsy.mapper.GoodsMapper;
import com.lsy.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李帅豫 on 2020/4/26.
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsVo> getList() {

        return goodsMapper.getList();

    }

    public GoodsVo findGoodsById(Long id) {
        return goodsMapper.findGoodsById(id);
    }
}
