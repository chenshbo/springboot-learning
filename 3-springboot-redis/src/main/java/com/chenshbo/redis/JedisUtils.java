package com.chenshbo.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis客户端分片方案
 */
public class JedisUtils {

    //	一些时间常量
    public static final Integer

            SECOND_HALF_HOURS = 60 * 30, //	半小时的秒数
            SECOND_HOURS = 60 * 60, //	一小时的秒数
            SECOND_DAY = 60 * 60 * 24, //	一天的秒数
            SECOND_WEEK = 60 * 60 * 24 * 7, //	一周的秒数
            SECOND_MONTH = SECOND_DAY * 30; //	一个月的秒数;

    protected ShardedJedisPool jedisPool;

    public void setJedisPool(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 借出jedis连接实例
     *
     * @return
     */
    protected ShardedJedis borrow() {
        return jedisPool.getResource();
    }

    /**
     * 用完了别忘了还回来
     */
    protected void returnConn(ShardedJedis jedis) {
        jedisPool.returnResource(jedis);
    }

}
