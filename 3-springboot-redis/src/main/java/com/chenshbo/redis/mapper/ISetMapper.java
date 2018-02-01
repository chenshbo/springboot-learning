package com.chenshbo.redis.mapper;

import redis.clients.jedis.ShardedJedis;

/**
 * 集合元素
 */
public interface ISetMapper extends IMapper {

    /**
     * 集合元素mapper函数
     *
     * @param value 每一项的实际值
     */
    public void mapper(ShardedJedis jedis, String value);

    /**
     * 集合元素项转成字符串类型
     */
    public String value();
}
