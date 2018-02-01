package com.chenshbo.redis.mapper;

import java.util.Map;

/**
 * hash数据结构 - 对象间的映射
 */
public interface IHashMapper extends IMapper {

    /**
     *
     */
    void mapper(Map<String, String> kv);

}
