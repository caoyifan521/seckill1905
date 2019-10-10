package com.qfedu.seckill1905.service.impl;

import com.qfedu.seckill1905.common.CodeMsg;
import com.qfedu.seckill1905.common.ErrorCode;
import com.qfedu.seckill1905.dao.UserDao;
import com.qfedu.seckill1905.entity.User;
import com.qfedu.seckill1905.exception.MyException;
import com.qfedu.seckill1905.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private CodeMsg codeMsg;


    @Override
    public User login(String phone, String password) {
        User user = userDao.findByPhone(phone);
        if(user == null){
            throw new MyException(ErrorCode.PHONE_ERROR, codeMsg.getPasswordEmpty());
        }
        if(!user.getPassword().equals(password)){
            throw new MyException(ErrorCode.PHONE_ERROR, codeMsg.getPasswordEmpty());
        }
        return user;
    }
}








