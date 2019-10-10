package com.qfedu.seckill1905.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // 自动生成对应的getter和setter方法
@ToString // 重写toString方法
@NoArgsConstructor // 生成无参的构造方法
@AllArgsConstructor // 生成带所有参数的构造方法
public class User {
    private Integer uid;
    private String phone;
    private String password;

}
