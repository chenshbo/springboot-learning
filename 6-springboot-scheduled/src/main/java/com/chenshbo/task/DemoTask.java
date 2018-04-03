package com.chenshbo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by user on 2018/4/3.
 */
@Component
public class DemoTask {

    @Scheduled(fixedRate = 5000)
    private void testTask(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }
}
