package com.chenshbo.controller;

import com.chenshbo.redis.JedisWriteUtils;
import com.chenshbo.vo.JsonResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Description: 模拟订单controller
 *
 * @author chenshangbo
 * @date 2018-01-24 14:27:21
 */
@RestController()
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisWriteUtils jedisWriteUtils;

    @GetMapping("/insert")
    public JsonResVO redisSet(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        jedisWriteUtils.lpush("order-queue", uuid);
        System.out.println(uuid+"新增一个请求。。。。。。");
        return new JsonResVO(200, "success", null);
    }
}
