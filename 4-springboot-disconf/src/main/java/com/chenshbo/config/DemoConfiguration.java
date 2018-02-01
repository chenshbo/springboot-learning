package com.chenshbo.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Description: disconf配置
 *
 * @author chenshangbo
 * @date 2018-02-01 14:10:40
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "demo.properties")
public class DemoConfiguration {

    /** 测试disconf配置 */
    private String test;

    @DisconfFileItem(name = "demo.test",associateField ="test")
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}