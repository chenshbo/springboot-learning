package com.chenshbo.req;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class HelloReq implements Serializable {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
