package com.chenshbo.redis;

import com.chenshbo.redis.mapper.IMapper;
import redis.clients.jedis.ShardedJedis;

import java.util.Map;

/**
 * Redis写入工具类
 */
public class JedisWriteUtils extends JedisUtils {

    /****************************************************************************************************************
     * 	普通键值对
     ****************************************************************************************************************/
    /**
     * 缓存键值对
     *
     * @param key
     * @param value
     * @param seconds 缓存超时时间，为空或小于0则永久有效（单位：秒）
     */
    public void save(String key, String value, Integer seconds) {
        ShardedJedis jedis = borrow();
        try {
            jedis.set(key, value);
            if (seconds != null && seconds > 0) {
                jedis.expire(key, seconds);
            }

        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 删除
     */
    public void del(String key) {
        ShardedJedis jedis = borrow();
        try {
            jedis.del(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 折半法重置key的有效期
     * <p>	如果剩余的有效期不到完整有效期的一半，则重置有效期。
     * <p>	适用场景：当key的有效期较长，又需要频繁更新有效期时。减少更新次数
     *
     * @param key
     * @param key
     * @param timeout 有效期（单位：秒）
     */
    public void expireHalf(String key, Integer timeout) {

        ShardedJedis jedis = borrow();
        try {
            Long ttl = jedis.ttl(key);
            //	如果key不存在则直接返回
            if (ttl == -2) {
                return;
            }
            //	如果key之前没设置过有效期则直接重置
            if (ttl == -1) {
                jedis.expire(key, timeout);
            }
            //	否则按折半法重置有效期
            else {
                if (ttl < timeout / 2) {
                    jedis.expire(key, timeout);
                }
            }
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 累加
     * <p>	如果key不存在，则置为incr.否则每次累加incr。若incr为空，则每次累加1
     *
     * @return 累加后的值
     */
    public Long incr(String key, Integer incr) {
        ShardedJedis jedis = borrow();
        if (incr == null) {
            incr = 1;
        }
        Long res = null;
        try {
            res = jedis.incrBy(key, incr);
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    /**
     * 累减
     * <p>	如果key不存在，则置为incr.否则每次累加incr。若incr为空，则每次累减1
     *
     * @return 累减后的值
     */
    public Long decr(String key, Integer decr) {
        ShardedJedis jedis = borrow();
        if (decr == null) {
            decr = 1;
        }
        Long res = null;
        try {
            res = jedis.decrBy(key, decr);
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * 若给定的 key 已经存在，则 根据规则设置过期时间
     * <p>	适用场景1：多机部署环境下的单机锁,setnx=1的获取锁，setnx！=1的没有锁
     *
     * @param key
     * @param value
     * @param seconds 缓存超时时间，为空或小于0则永久有效（单位：秒）
     * @return 设置成功，返回 1 。 设置失败，返回 0 。请求出错返回null
     */
    public Long setnx(String key, String value, Integer seconds) {
        ShardedJedis jedis = borrow();
        Long res = null;
        try {
            res = jedis.setnx(key, value);
            // 只有设置成功的时候设置过期时间，防止重复设置过期时间
            if (res != null && res.longValue() == 1) {
                if (seconds != null && seconds > 0) {
                    jedis.expire(key, seconds);
                }
            } else {
                //	检查过期时间，如果key之前没设置过有效期则直接重置,防止突然性宕机导致的redis key永久有效
                Long ttl = jedis.ttl(key);
                if (ttl != null && ttl.longValue() == -1 && seconds != null && seconds > 0) {
                    jedis.expire(key, seconds);
                }
            }
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    /****************************************************************************************************************
     * 	Hash数据类型
     ****************************************************************************************************************/
    /**
     * 给某一个field累加
     */
    public Long hincrBy(String key, String field, Integer incr) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hincrBy(key, field, incr);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 给某一个field累加
     */
    public Double hincrByFloat(String key, String field, Float incr) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hincrByFloat(key, field, incr);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 设置字段的值
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.hset(key, field, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 删除hash中的field
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        ShardedJedis jedis = borrow();
        try {
            jedis.hdel(key, field);
        } finally {
            returnConn(jedis);
        }
    }

    /****************************************************************************************************************
     * 	List数据类型
     ****************************************************************************************************************/
    /**
     * 从头部插入元素
     *
     * @param key
     * @param value
     */
    public void listPush(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.lpush(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 从尾部插入元素
     */
    public void listRPush(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.rpush(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 删除队列尾部一个元素
     */
    public void listPop(String key) {
        ShardedJedis jedis = borrow();
        try {
            jedis.rpop(key);
        } finally {
            returnConn(jedis);
        }
    }

    public void rpush(String key, String... values) {
        ShardedJedis jedis = borrow();
        try {
            jedis.rpush(key, values);
        } finally {
            returnConn(jedis);
        }
    }

    public void lrem(String key, long count, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.lrem(key, count, value);
        } finally {
            returnConn(jedis);
        }
    }

    public String rpop(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.rpop(key);
        } finally {
            returnConn(jedis);
        }
    }

    public void lpush(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.lpush(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 对一个列表进行修剪(trim)，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *
     * @param key   缓存key
     * @param start 要保留区间的起始位置
     * @param end   要保留区间的结束位置
     * @return
     */
    public String ltrim(String key, long start, long end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.ltrim(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /****************************************************************************************************************
     * 	Set数据类型
     ****************************************************************************************************************/
    /**
     * 往一个Set里塞记录
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.sadd(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * set里移除一个元素
     *
     * @param key
     * @param value
     */
    public void srem(String key, String... value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.srem(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 移除排序集合下标在给定区间内的元素
     * <p>	score为从小到大顺序
     *
     * @param end 结束位置。end为负的话表示倒数第几个元素
     */
    public Long zsetDeleteIndexLimit(String key, Integer start, Integer end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zremrangeByRank(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 移除排序集合score在给定区间的元素
     * <p>	score为从小到大顺序
     */
    public Long zsetDeleteScoreLimit(String key, double scoreMin, double scoreMax) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zremrangeByScore(key, scoreMin, scoreMax);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 删除zset中值为value的元素
     *
     * @param key
     * @param value
     */
    public void zrem(String key, String... value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.zrem(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 删除正序中索引位置在start和end之间的元素。包括start
     *
     * @param key
     * @param start
     * @param end
     */
    public void zremrangeByRank(String key, long start, long end) {
        ShardedJedis jedis = borrow();
        try {
            jedis.zremrangeByRank(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 保存sort set元素项
     *
     * @param key
     * @param score
     * @param value
     */
    public void zadd(String key, double score, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.zadd(key, score, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 保存sort set元素项
     */
    public void zadd(String key, Map<String, Double> values) {
        ShardedJedis jedis = borrow();
        try {
            jedis.zadd(key, values);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 给sort set中的元素追加score
     *
     * @param key
     * @param score
     * @param value
     */
    public void zincrby(String key, double score, String value) {
        ShardedJedis jedis = borrow();
        try {
            jedis.zincrby(key, score, value);
        } finally {
            returnConn(jedis);
        }
    }

    public Long zremrangeByScore(String key, double start, double end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 缓存数据类型
     *
     * @param mapper  需要缓存的数据类型
     * @param seconds 缓存超时时间。为空或小于0则永久有效（单位：秒）
     */
    public void save(IMapper mapper, Integer seconds) {
        ShardedJedis jedis = borrow();
        try {
            mapper.save(jedis);
            if (seconds != null && seconds > 0) {
                jedis.expire(mapper.key(), seconds);
            }
        } finally {
            returnConn(jedis);
        }
    }

}
