package com.qfedu.seckill1905.service.impl;

import com.qfedu.seckill1905.dao.OrdersDao;
import com.qfedu.seckill1905.entity.Orders;
import com.qfedu.seckill1905.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired(required = false)
    private OrdersDao ordersDao;

    @Override
    public void addOrder(Orders orders) {
        ordersDao.insert(orders);
    }

    @Override
    public List<Orders> findByUid(Integer uid) {
        return ordersDao.selectAll(uid);
    }
}
