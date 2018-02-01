package com.chenshbo.controller;

import com.chenshbo.req.BaseReq;
import com.chenshbo.vo.JsonResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: hello
 *
 * @author chenshangbo
 * @date 2018-01-31 09:47:36
 */
@Api(value = "API DEMO")
@RestController()
public class DemoController {

    @ApiOperation(value = "获取编码", notes = "根据code获取编码名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value="编码",required = true,paramType = "query",dataType = "String"),
    })
    @GetMapping("/get")
    public JsonResVO getDemo(@RequestParam(value = "code") String code) {
        String name;
        switch (code) {
            case "1" : name = "一"; break;
            case "2" : name = "二"; break;
            default : name = "无";
        }
        return new JsonResVO(200, "success", name);
    }

    @ApiOperation(value = "分页查询接口", notes = "")
    @ApiImplicitParam(name = "req", value = "分页请求对象", required = true, dataType = "BaseReq")
    @PostMapping(value = "/queryByPage")
    public JsonResVO insertLogDailyWork(@RequestBody BaseReq req) {
        return new JsonResVO(200, "success", req);
    }

}
