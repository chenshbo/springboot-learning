package com.chenshbo.req;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class UserInsertReq implements Serializable{

    private static final long serialVersionUID = -8404762487105882249L;

    /** 用户id */
    private Long id;

    /** 用户名 */
    private String userName;

    /** 用户密码 */
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
