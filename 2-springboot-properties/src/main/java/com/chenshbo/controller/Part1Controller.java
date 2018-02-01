package com.chenshbo.controller;

import com.chenshbo.vo.JsonResVO;
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
@RequestMapping("/part1")
public class Part1Controller {

    @Value("${custom.name1}")
    private String customName;

    @Value("${custom.password1}")
    private String customPassword;

    @GetMapping("/get")
    public JsonResVO get() {
        return new JsonResVO(200, "success", customName + "-" + customPassword);
    }

}
