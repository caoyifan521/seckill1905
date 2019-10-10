package com.qfedu.seckill1905.controller;

import com.qfedu.seckill1905.entity.Orders;
import com.qfedu.seckill1905.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/orders")
    public String orderPage(Integer uid, Model model){

        List<Orders> list = ordersService.findByUid(uid);
        model.addAttribute("orderList", list);

        return "orderDetail";
    }

}
