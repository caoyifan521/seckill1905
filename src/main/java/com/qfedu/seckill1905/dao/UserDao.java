package com.qfedu.seckill1905.dao;

import com.qfedu.seckill1905.entity.User;

public interface UserDao {
    public User findByPhone(String phone);
}
