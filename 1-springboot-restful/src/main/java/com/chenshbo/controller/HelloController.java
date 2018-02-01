package com.chenshbo.controller;

import com.chenshbo.req.HelloReq;
import com.chenshbo.vo.JsonResVO;
import org.springframework.web.bind.annotation.*;

/**
 * Description: hello
 *
 * @author chenshangbo
 * @date 2018-01-31 09:47:36
 */
@RestController()
public class HelloController {

    @GetMapping("/hello-get")
    public JsonResVO helloGet(@RequestParam String key){
        return new JsonResVO(200, "success", key+"GET");
    }

    @PostMapping("/hello-post")
    public JsonResVO helloPost(@RequestBody HelloReq req){
        return new JsonResVO(200, "success", req.getKey()+"POST");
    }
}
