package com.qfedu.seckill1905.dao;

import com.qfedu.seckill1905.entity.SeckillGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeckillDao {

    public List<SeckillGoods> findAll();

    public SeckillGoods findById(Integer sid);

    public void updateStockById(Integer sid);

    public int getStock(Integer sid);

    //更新URL静态页面
    public void updateUrlById(@Param("sid") Integer sid,@Param("url") String url);

}
