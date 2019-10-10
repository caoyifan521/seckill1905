package com.qfedu.seckill1905.service.impl;

import com.qfedu.seckill1905.dao.OrdersDao;
import com.qfedu.seckill1905.dao.SeckillDao;
import com.qfedu.seckill1905.entity.SeckillGoods;
import com.qfedu.seckill1905.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {

    // 线程安全的map结构
    private ConcurrentHashMap<String, Boolean> overMap = new ConcurrentHashMap<>();

    @Autowired(required = false)
    private SeckillDao seckillDao;

    @Autowired(required = false)
    private OrdersDao orderDao;

    @Override
    public List<SeckillGoods> findAllSeckillGoods() {
        //System.out.println("123");
        return seckillDao.findAll();
    }

    @Override
    public Map<String, Object> findSeckillGoodsById(Integer sid) {
        Map<String, Object> map = new HashMap<>();

        SeckillGoods seckillGoods = seckillDao.findById(sid);
        long remainTime = 0;// 秒杀开始前的剩余时间
        int seckillState = 0; // 秒杀所处状态，-1 表示未开始，0 进行中 1 结束


        Date beginTime = seckillGoods.getBeginTime();
        Date endTime = seckillGoods.getEndTime();
        Date currentTime = new Date();
        long interval = beginTime.getTime() - currentTime.getTime();
        if (interval > 0) { // 秒杀还没开始
            remainTime = interval / 1000;
            seckillState = -1;
        } else {
            long endInterval = endTime.getTime() - currentTime.getTime();
            if (endInterval < 0) { // 当前时间超过结束时间，秒杀结束
                seckillState = 1;
                remainTime = -1;
            } else { // 当前时间在开始和结束中间
                // 获取秒杀商品的剩余库存
                Double stock = seckillGoods.getSeckillStock();
                if (stock <= 0) { // 秒杀结束
                    seckillState = 1;
                    remainTime = -1;
                } else {
                    seckillState = 0; // 秒杀进行中
                    remainTime = 0;

                }
            }
        }


        map.put("seckill", seckillGoods);
        map.put("remainTime", remainTime);
        map.put("seckillState", seckillState);


        return map;

    }

    @Override
    public SeckillGoods findSeckillGoodsBySid(Integer sid) {
        SeckillGoods seckillGoods = seckillDao.findById(sid);
        return seckillGoods;
    }

    @Override
    public void updateSeckillUrlById(Integer sid, String url) {
        seckillDao.updateUrlById(sid,url);
    }

    @Override
    public Map<String, Object> findSeckillGoodsById2(Integer sid) {
        Map<String, Object> map = new HashMap<>();

        SeckillGoods seckillGoods = seckillDao.findById(sid);
        long remainTime = 0;// 秒杀开始前的剩余时间
        int seckillState = 0; // 秒杀所处状态，-1 表示未开始，0 进行中 1 结束


        Date beginTime = seckillGoods.getBeginTime();
        Date endTime = seckillGoods.getEndTime();
        Date currentTime = new Date();
        long interval = beginTime.getTime() - currentTime.getTime();
        if (interval > 0) { // 秒杀还没开始
            remainTime = interval / 1000;
            seckillState = -1;
        } else {
            long endInterval = endTime.getTime() - currentTime.getTime();
            if (endInterval < 0) { // 当前时间超过结束时间，秒杀结束
                seckillState = 1;
                remainTime = -1;
            } else { // 当前时间在开始和结束中间
                // 获取秒杀商品的剩余库存
                Double stock = seckillGoods.getSeckillStock();
                if (stock <= 0) { // 秒杀结束
                    seckillState = 1;
                    remainTime = -1;
                } else {
                    seckillState = 0; // 秒杀进行中
                    remainTime = 0;

                }
            }
        }


        map.put("seckill", seckillGoods);
        map.put("remainTime", remainTime);
        map.put("seckillState", seckillState);


        return map;



    }

    @Override
    public void buySeckillGoods(Integer uid, Integer sid) {

        // 判断是否有库存
        int stock = seckillDao.getStock(sid);
        if (stock > 0) {
            // 库存-1
            seckillDao.updateStockById(sid);

            // 生成订单
            // 修改实际库存，写入订单
            orderDao.updateOrder(uid, sid);
        } else {
            throw new RuntimeException("已经秒杀完了");
        }

    }


}
