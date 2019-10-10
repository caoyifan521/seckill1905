package com.qfedu.seckill1905;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Seckill1905ApplicationTests {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		// 操作普通的key-value数据（value类型时字符串）
		redisTemplate.opsForValue().set("name", "zhangsan");

		String name = redisTemplate.opsForValue().get("name");

		redisTemplate.opsForHash().put("user", "name", "zhangsan");
		redisTemplate.opsForHash().put("user", "age", "20");

		System.out.println(name);

		// 底层调用setnx命令
		// 如果值不存在，添加，返回true；如果存在，不处理，返回false
		Boolean ret = redisTemplate.opsForValue().setIfAbsent("qq", "123");
		System.out.println(ret);

	}

}
