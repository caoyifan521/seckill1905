package com.qfedu.seckill1905.service;


import com.qfedu.seckill1905.entity.Orders;

import java.util.List;

public interface OrdersService {

    public void addOrder(Orders orders);

    public List<Orders> findByUid(Integer uid);
}
