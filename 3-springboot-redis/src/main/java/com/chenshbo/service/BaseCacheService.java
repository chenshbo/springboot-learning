package com.chenshbo.service;

import com.chenshbo.entity.BaseEntity;
import com.chenshbo.redis.mapper.CacheVo;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public interface BaseCacheService<T extends BaseEntity> {

    void save(CacheVo<T> cacheVo);

    CacheVo<T> get(Long id);

}
