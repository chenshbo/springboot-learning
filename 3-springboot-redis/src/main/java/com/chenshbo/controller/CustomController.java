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

/**
 * Description: 客户controller
 *
 * @author chenshangbo
 * @date 2018-01-24 14:27:21
 */
@RestController()
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomCacheService customCacheService;

    @PostMapping("/insert")
    public JsonResVO insert(@RequestBody Custom custom){
        customCacheService.save(new CacheVo<>(custom));
        return new JsonResVO(200, "success", null);
    }

    @PostMapping("/get")
    public JsonResVO get(@RequestBody Custom custom){
        CacheVo<Custom> customCacheVo = customCacheService.get(custom.getId());
        if(customCacheVo == null){
            return new JsonResVO(300, "failure", null);
        }
        return new JsonResVO(200, "success", customCacheVo.getObj());
    }
}
