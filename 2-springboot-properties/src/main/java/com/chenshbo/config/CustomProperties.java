package com.chenshbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description: 自定义配置
 * 
 * @author chenshangbo
 * @date 2018-01-31 10:27:28
 */
@Component
@PropertySource(value = "classpath:custom.properties")
public class CustomProperties {

    @Value("${custom2.name}")
    private String customName;

    @Value("${custom2.password}")
    private String customPassword;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomPassword() {
        return customPassword;
    }

    public void setCustomPassword(String customPassword) {
        this.customPassword = customPassword;
    }
}
