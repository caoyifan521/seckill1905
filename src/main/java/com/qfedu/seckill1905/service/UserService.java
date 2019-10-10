package com.qfedu.seckill1905.service;

import com.qfedu.seckill1905.entity.User;

public interface UserService {
    public User login(String phone, String password);
}
