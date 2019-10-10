package com.qfedu.seckill1905.controller;

import com.qfedu.seckill1905.common.CodeMsg;
import com.qfedu.seckill1905.common.ErrorCode;
import com.qfedu.seckill1905.common.JsonResult;
import com.qfedu.seckill1905.entity.SeckillGoods;
import com.qfedu.seckill1905.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@RestController
public class ThymeleafController {

    @Autowired
    @Qualifier("myTemplateEngine")
    private TemplateEngine templateEngine;


    @Autowired
    private SeckillService seckillService;

    @Autowired
    private CodeMsg codeMsg;


    @RequestMapping("/createHtml")
    public JsonResult createHtml(Integer sid) {

        //查询商品秒杀的信息
        SeckillGoods seckillGoods = seckillService.findSeckillGoodsBySid(sid);

        Context context = new Context();
        context.setVariable("seckillGoods", seckillGoods);

        //seckillDetail—s1.html
        //seckillDetail—s2.html
        String fileName = "seckillDetail—s" + sid + ".html";

        //获取classes目录的实际路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

        path += "/static";
        File file = new File(path, fileName);
        //如果存在先删除
        if (file.exists()) {
            file.delete();
        }
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);

            //生成静态页面
            //第一个参数，模板引擎得名称
            //第二个参数，上下文对象
            //第三个参数，生成的html文件的Writer对象
            templateEngine.process("seckillDetail—s", context, writer);


            //修改url字段
            seckillService.updateSeckillUrlById(sid,fileName);

        } catch (Exception e) {
            e.printStackTrace();

            return new JsonResult(ErrorCode.CREATE_HTML_ERROR, codeMsg.getCreateHtmlError());
        } finally {
            if (writer != null) {
                try {
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return new JsonResult(0, null);
    }


}
