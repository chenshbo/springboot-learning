package com.chenshbo.req;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public class BaseReq {

    @ApiModelProperty(value = "当前页")
    private Long page;

    @ApiModelProperty(value = "每页数")
    private Long pageNum;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
}
