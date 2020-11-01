package com.hackerstudy.adminclient.controller.user;

import com.hackerstudy.adminclient.common.annotation.ResponseResult;
import com.hackerstudy.adminclient.common.vo.JSONResult;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.IMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @class: UserController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-04 14:57
 */
@Api(description = "用户信息的API接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IMService imService;

    @ApiOperation(value="获取张三的信息", notes="获取张三的信息")
    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123456");
        user.setAge(20);
        user.setBirthday(new Date());
        return user;
    }

    @ApiOperation(value="获取张三的信息(返回json数据)", notes="获取张三的信息(返回json数据)", produces="application/json")
    @ResponseResult
    @RequestMapping("/getUserJson")
    public User getUserJson() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123456");
        user.setAge(20);
        user.setBirthday(new Date());
        user.setDesc(null);
        return user;
    }

    @RequestMapping("/testExceptionHandle")
    public JSONResult<User> testExceptionHandle() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123456");
        user.setAge(20 / 0);
        user.setBirthday(new Date());
        user.setDesc(null);
        return JSONResult.ok(user);
    }

    @GetMapping("/getAllUser")
    public JSONResult<List<User>> getAllUser() {
        List<User> chatRoomUser = imService.getChatRoomUser();
        return JSONResult.ok(chatRoomUser);
    }
}
