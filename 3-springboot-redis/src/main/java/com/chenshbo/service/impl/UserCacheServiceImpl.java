package com.chenshbo.service.impl;

import com.chenshbo.entity.User;
import com.chenshbo.service.UserCacheService;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
@Component
public class UserCacheServiceImpl extends BaseCacheServiceImpl<User> implements UserCacheService {

    public UserCacheServiceImpl() {
        super(User.class);
    }
}
