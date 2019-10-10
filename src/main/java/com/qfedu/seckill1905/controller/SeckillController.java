package com.qfedu.seckill1905.controller;

import com.qfedu.seckill1905.common.JsonResult;
import com.qfedu.seckill1905.entity.SeckillGoods;
import com.qfedu.seckill1905.entity.User;
import com.qfedu.seckill1905.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/seckill/list")
    public String listSeckillGoods(Model model){
        List<SeckillGoods> list = seckillService.findAllSeckillGoods();
        model.addAttribute("seckillList", list);
        return "seckillList";
    }

    @RequestMapping("/seckill/detail")
    public String findSeckillGoodsById(Integer sid, Model model){

        Map<String, Object> map = seckillService.findSeckillGoodsById(sid);
        model.addAttribute("seckillInfo", map);

        return "seckillDetail";

    }

    @RequestMapping("/seckill/buy")
    public String buySeckillGoods(Integer sid, HttpSession session){

        User user = (User)session.getAttribute("loginuser");
        if(user == null){
            throw new RuntimeException("未登陆");
        }
        seckillService.buySeckillGoods(user.getUid(), sid);

        return "redirect:/orders?uid=" + user.getUid();
    }


    //详情页面静态化后，获取库存/秒杀等数据
    @RequestMapping("/seckillinfo")
    @ResponseBody
    public JsonResult seckillInfo(Integer sid){
        Map<String, Object> map = seckillService.findSeckillGoodsById2(sid);
        return new JsonResult(0,map);
    }

}
