package com.chenshbo.controller;

import com.chenshbo.config.CustomProperties;
import com.chenshbo.vo.JsonResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 自定义配置文件
 *
 * @author chenshangbo
 * @date 2018-01-31 09:47:36
 */
@RestController()
@RequestMapping("/part2")
public class Part2Controller {

    @Autowired
    private CustomProperties customProperties;

    @GetMapping("/get")
    public JsonResVO get() {
        return new JsonResVO(200, "success", customProperties.getCustomName() + "-" + customProperties.getCustomPassword());
    }

}
