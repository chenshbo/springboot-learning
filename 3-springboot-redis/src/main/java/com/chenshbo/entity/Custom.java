package com.chenshbo.entity;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class Custom extends BaseEntity{

    private Long id;

    private String customName;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
