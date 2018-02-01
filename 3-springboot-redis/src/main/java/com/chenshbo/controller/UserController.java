package com.chenshbo.controller;

import com.chenshbo.entity.Custom;
import com.chenshbo.entity.User;
import com.chenshbo.redis.mapper.CacheVo;
import com.chenshbo.service.CustomCacheService;
import com.chenshbo.service.UserCacheService;
import com.chenshbo.vo.JsonResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Description: 模拟订单controller
 *
 * @author chenshangbo
 * @date 2018-01-24 14:27:21
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private CustomCacheService customCacheService;

    @PostMapping("/insert")
    public JsonResVO insertUser(@RequestBody User user){
        CacheVo<User> userCacheVo = new CacheVo<>(user);
        userCacheService.save(userCacheVo);
        return new JsonResVO(200, "success", null);
    }

    @PostMapping("/get")
    public JsonResVO getUser(@RequestBody User user){
        CacheVo<User> userCacheVo = userCacheService.get(user.getId());
        if(userCacheVo == null){
            return new JsonResVO(300, "failure", null);
        }
        return new JsonResVO(200, "success", userCacheVo.getObj());
    }

    @PostMapping("/insertCustom")
    public JsonResVO insertCustom(@RequestBody Custom custom){
        customCacheService.save(new CacheVo<>(custom));
        return new JsonResVO(200, "success", null);
    }

    @PostMapping("/getCustom")
    public JsonResVO getCustom(@RequestBody Custom custom){
        CacheVo<Custom> customCacheVo = customCacheService.get(custom.getId());
        if(customCacheVo == null){
            return new JsonResVO(300, "failure", null);
        }
        return new JsonResVO(200, "success", customCacheVo.getObj());
    }
}
