package com.chenshbo.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class User extends BaseEntity{

    /** 用户id */
    private Long id;

    /** 用户名 */
    private String userName;

    /** 用户密码 */
    private String password;

    /** 创建时间 */
    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
