package com.chenshbo.service.impl;

import com.chenshbo.entity.BaseEntity;
import com.chenshbo.redis.JedisReadUtils;
import com.chenshbo.redis.JedisUtils;
import com.chenshbo.redis.JedisWriteUtils;
import com.chenshbo.redis.mapper.CacheVo;
import com.chenshbo.service.BaseCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class BaseCacheServiceImpl<T extends BaseEntity> implements BaseCacheService<T>{

    @Autowired
    private JedisReadUtils jedisReadUtils;

    @Autowired
    private JedisWriteUtils jedisWriteUtils;

    protected String modelName;

    protected T obj;

    public BaseCacheServiceImpl(Class<T> modelClass) {
        this.modelName = StringUtils.uncapitalize(modelClass.getSimpleName());
        try {
            this.obj = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(CacheVo cacheVo) {
        jedisWriteUtils.save(cacheVo, JedisUtils.SECOND_WEEK*2);
    }

    @Override
    public CacheVo<T> get(Long id) {
        String key = modelName + ":" +id;
        if (jedisReadUtils.exists(key)) {
            jedisWriteUtils.expireHalf(key, JedisUtils.SECOND_WEEK * 2);
            return new CacheVo(obj, jedisReadUtils.hgetAll(key));
        }
        return null;
    }
}
