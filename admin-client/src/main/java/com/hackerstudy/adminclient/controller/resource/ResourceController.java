package com.hackerstudy.adminclient.controller.resource;

import com.hackerstudy.adminclient.common.vo.JSONResult;
import com.hackerstudy.adminclient.pojo.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class: ResourceController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-09 10:12
 */
@Api(description = "获取资源数据的API接口")
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    Resource resource;

    @RequestMapping("/testHello")
    public String testHello(){
        return "hello world";
    }

    @ApiOperation(value="获取资源文件数据", notes="获取资源文件数据", produces="application/json")
    @GetMapping("/getResource")
    public JSONResult getResource(){
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return JSONResult.ok(bean);
    }
}
