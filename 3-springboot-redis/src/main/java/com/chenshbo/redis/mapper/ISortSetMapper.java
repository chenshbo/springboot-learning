package com.chenshbo.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * 排序集合
 */
public interface ISortSetMapper extends ISetMapper {

    /**
     * 集合元素mapper函数
     *
     * @param value 每一项的实际值
     * @param score 每一项的排序值
     */
    public void mapper(ShardedJedis jedis, String value, Double score);

    /**
     *
     */
    public Double score();
}
