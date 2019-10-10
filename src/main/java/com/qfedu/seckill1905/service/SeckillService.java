package com.qfedu.seckill1905.service;

import com.qfedu.seckill1905.entity.SeckillGoods;

import java.util.List;
import java.util.Map;

public interface SeckillService {
    public List<SeckillGoods> findAllSeckillGoods();

    public Map<String, Object> findSeckillGoodsById(Integer sid);

    public void buySeckillGoods(Integer uid, Integer sid);


    //-----------------静态化页面
    //根据秒杀商品的ID 仅获取秒杀商品信息
    public SeckillGoods findSeckillGoodsBySid(Integer sid);

    //更新页面的路径
    public void updateSeckillUrlById(Integer sid,String url);

    //获取库存秒杀状态等信息
    public Map<String, Object> findSeckillGoodsById2(Integer sid);



}
