package com.qfedu.seckill1905.controller;

import com.qfedu.seckill1905.common.JsonResult;
import com.qfedu.seckill1905.entity.User;
import com.qfedu.seckill1905.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public JsonResult login(String phone, String password, HttpSession session){
        User user = userService.login(phone, password);
        session.setAttribute("loginuser", user);
        return new JsonResult(1, null);
    }

}
