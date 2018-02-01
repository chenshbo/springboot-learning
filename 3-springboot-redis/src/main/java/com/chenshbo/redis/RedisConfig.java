package com.chenshbo.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: redis配置
 * 
 * @author chenshangbo
 * @date 2018-01-31 15:15:35
 */
@Component
@PropertySource(value = "classpath:redis.properties")
public class RedisConfig {

    @Value("${redis.server.host}")
    private String host;
    @Value("${redis.server.port}")
    private Integer port;
    @Value("${redis.server.connectionTimeout}")
    private Integer connectionTimeout;
    @Value("${redis.server.soTimeout}")
    private Integer soTimeout;
    @Value("${redis.server.weight}")
    private int weight;
    @Value("${redis.server.password}")
    private String password;
    @Value("${redis.server.blockWhenExhausted}")
    private boolean blockWhenExhausted;
    @Value("${redis.server.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${redis.server.minIdle}")
    private int minIdle;
    @Value("${redis.server.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${redis.server.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;
    @Value("${redis.server.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.server.maxIdle}")
    private int maxIdle;
    @Value("${redis.server.maxTotal}")
    private int maxTotal;

    protected JedisPoolConfig getGenericObjectPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setBlockWhenExhausted(blockWhenExhausted);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setTestWhileIdle(testWhileIdle);
        poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }

    protected JedisShardInfo getShard1Read() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight);
        jedisShardInfo.setPassword(password);
        return jedisShardInfo;
    }

    protected JedisShardInfo getShard1Write() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight);
        jedisShardInfo.setPassword(password);
        return jedisShardInfo;
    }

    protected ShardedJedisPool getJedisReadShardPool() {
        List<JedisShardInfo> shardInfos = new ArrayList<>();
        shardInfos.add(getShard1Read());
        return new ShardedJedisPool(getGenericObjectPoolConfig(), shardInfos);
    }

    protected ShardedJedisPool getJedisWriteShardPool() {
        List<JedisShardInfo> shardInfos = new ArrayList<>();
        shardInfos.add(getShard1Write());
        return new ShardedJedisPool(getGenericObjectPoolConfig(), shardInfos);
    }

    @Bean(name = "jedisReadUtils")
    public JedisReadUtils getJedisReadUtils() {
        JedisReadUtils jedisReadUtils = new JedisReadUtils();
        jedisReadUtils.setJedisPool(getJedisReadShardPool());
        return jedisReadUtils;
    }

    @Bean(name = "jedisWriteUtils")
    public JedisWriteUtils getJedisWriteUtils() {
        JedisWriteUtils jedisWriteUtils = new JedisWriteUtils();
        jedisWriteUtils.setJedisPool(getJedisWriteShardPool());
        return jedisWriteUtils;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }
}
