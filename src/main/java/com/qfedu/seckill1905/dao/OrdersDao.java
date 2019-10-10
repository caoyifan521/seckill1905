package com.qfedu.seckill1905.dao;


import com.qfedu.seckill1905.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersDao {

    public void insert(Orders orders);

    public List<Orders> selectAll(Integer uid);

    public void updateOrder(@Param("sid") Integer sid, @Param("uid") Integer uid);

}
