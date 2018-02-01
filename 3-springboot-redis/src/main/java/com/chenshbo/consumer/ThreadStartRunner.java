package com.chenshbo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
@Component
public class ThreadStartRunner implements ApplicationRunner {

    @Autowired
    private JedisConsumer jedisConsumer;

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        jedisConsumer.run();
    }

}
