package com.qfedu.seckill1905.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private Integer orderId;
    private Goods goods;
    private Double totalPrice;
    private Date createTime;
    private Integer status;
    private User user;

}
