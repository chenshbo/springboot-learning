package com.chenshbo.service.impl;

import com.chenshbo.entity.Custom;
import com.chenshbo.redis.mapper.CacheVo;
import com.chenshbo.service.CustomCacheService;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
@Component
public class CustomCacheServiceImpl extends BaseCacheServiceImpl<Custom> implements CustomCacheService{

    public CustomCacheServiceImpl() {
        super(Custom.class);
    }
}
