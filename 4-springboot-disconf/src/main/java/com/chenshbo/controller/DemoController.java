package com.chenshbo.controller;

import com.chenshbo.config.DemoConfiguration;
import com.chenshbo.vo.JsonResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: hello
 *
 * @author chenshangbo
 * @date 2018-01-31 09:47:36
 */
@RestController()
public class DemoController {

    @Autowired
    private DemoConfiguration demoConfiguration;

    @GetMapping("/getDisconf")
    public JsonResVO getDisconf() {
        return new JsonResVO(200, "success", demoConfiguration.getTest());
    }

}
