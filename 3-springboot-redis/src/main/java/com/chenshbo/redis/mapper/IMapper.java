package com.chenshbo.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * redis - object之间的映射
 */
public interface IMapper {

    /**
     * 缓存项的key
     */
    String key();

    /**
     * 保存
     */
    void save(ShardedJedis jedis);
}
