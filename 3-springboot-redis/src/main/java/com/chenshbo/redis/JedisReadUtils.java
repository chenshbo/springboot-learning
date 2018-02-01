package com.chenshbo.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis读取工具类
 */
public class JedisReadUtils extends JedisUtils {

    /****************************************************************************************************************
     * 	普通键值对
     ****************************************************************************************************************/
    /**
     * 取缓存的键值对
     */
    public String get(String key) {
        ShardedJedis jedis = borrow();
        String res = null;
        try {
            res = jedis.get(key);
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    public String keys(String key) {
        ShardedJedis jedis = borrow();
        String res = null;
        try {
            res = jedis.get(key);
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    /**
     * 检测key是否存在
     */
    public boolean exists(String key) {
        ShardedJedis jedis = borrow();
        boolean res = false;

        try {
            res = jedis.exists(key);
        } finally {
            returnConn(jedis);
        }
        return res;
    }

    /**
     * 设置key有效期
     */
    public void expire(String key, int seconds) {
        ShardedJedis jedis = borrow();
        try {
            jedis.expire(key, seconds);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 获取一个key的剩余有效时间
     *
     * @return <p>	当key不存在返回-2
     * <p>	当key存在但没有设置过期时间返回-1
     * <p>	否则返回秒为单位的时间
     */
    public Long ttl(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.ttl(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取hash所有的键值对
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hgetAll(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 判断hash中是否存在field
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hexists(key, field);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取hash中的字段值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hget(key, field);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 批量取
     */
    public List<String> hmget(String key, String... fields) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hmget(key, fields);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取hash中的field个数
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.hlen(key);
        } finally {
            returnConn(jedis);
        }
    }

    /****************************************************************************************************************
     * 	List数据类型
     ****************************************************************************************************************/
    /**
     * 取列表长度
     */
    public Long listLength(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.llen(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 返回排序好的队列
     *
     * @param key
     */
    public List<String> list(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.sort(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * lrange操作
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, int start, int end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /****************************************************************************************************************
     * 	Set数据类型
     ****************************************************************************************************************/
    /**
     * 是否包含某个元素
     *
     * @param key
     * @param value
     * @return
     */
    public boolean scontains(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.sismember(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取set元素个数
     */
    public Long setLength(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.scard(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean sismember(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.sismember(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取set中的所有元素
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.smembers(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取set集合长度
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.scard(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 随机取元素
     */
    public String anyItem(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.srandmember(key);
        } finally {
            returnConn(jedis);
        }
    }

    /****************************************************************************************************************
     * 	Sort Set数据类型
     ****************************************************************************************************************/
    /**
     * 取排序集合元素个数
     */
    public Long zsetLength(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zcard(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 排序集合中score在给定区间的元素个数
     */
    public Long zsetLengthLimit(String key, double scoreMin, double scoreMax) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zcount(key, scoreMin, scoreMax);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取元素在sort set中的反序索引位置
     *
     * @param key
     * @param value
     * @return
     */
    public Long zrevrank(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrevrank(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 反序取元素集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, long start, long end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrevrange(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 反序取元素集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrevrangeWithScores(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取元素的score值
     *
     * @param key
     * @param value
     * @return
     */
    public Double zscore(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zscore(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取元素在sort set中的正序位置
     *
     * @param key
     * @param value
     * @return
     */
    public Long zrank(String key, String value) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrank(key, value);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 正序取元素集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, long start, long end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrange(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取sort set的长度
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zcard(key);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取sort set集合(从小到大)中score在start和end之间的元素。包含start
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrangeByScore(String key, double start, double end) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrangeByScore(key, start, end);
        } finally {
            returnConn(jedis);
        }
    }

    /**
     * 取sort set集合(从大到小)中score在start和end之间的元素。包含start
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<String> zrevrangeByScore(String key, long max, long min) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            returnConn(jedis);
        }
    }

    public String lpop(String key) {
        ShardedJedis jedis = borrow();
        try {
            return jedis.lpop(key);
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

}
