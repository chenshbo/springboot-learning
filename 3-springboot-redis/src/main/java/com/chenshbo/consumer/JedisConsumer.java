package com.chenshbo.consumer;

import com.chenshbo.redis.JedisReadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Description: redis实现mq
 *
 * @author chenshangbo
 * @date 2018-01-31 15:19:51
 */
@Component
public class JedisConsumer implements Runnable{

    @Autowired
    private JedisReadUtils jedisReadUtils;


    @Override
    public void run() {
        while (true){
            String orderId = jedisReadUtils.lpop("order-queue");
            if(!StringUtils.isEmpty(orderId)){
                System.out.println(orderId + "订单下单成功");
            }
        }
    }
}
