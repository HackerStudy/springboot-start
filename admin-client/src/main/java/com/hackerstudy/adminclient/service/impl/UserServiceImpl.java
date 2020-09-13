package com.hackerstudy.adminclient.service.impl;

import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.UserService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Date;

/**
 * @class: UserServiceImpl
 * @description: userwebservice的实现类
 * @author: HackerStudy
 * @date: 2020-07-29 21:34
 */
@Component
@WebService(name = "UserService", targetNamespace = "http://service.adminclient.hackerstudy.com",
        endpointInterface = "com.hackerstudy.adminclient.service.UserService")
public class UserServiceImpl implements UserService {

    //@Override
    //    //@WebMethod
    //    //@WebResult(name = "User")
    //    //public User getUserInfo(@WebParam(name = "userId") int userId) {
    //    //    User user = new User();
    //    //    user.setId(userId);
    //    //    user.setName("张三");
    //    //    user.setAge(24);
    //    //    user.setPassword("123456");
    //    //    user.setBirthday(new Date());
    //    //    user.setDesc("用户张三的信息");
    //    //    return user;
    //    //}

    @Override
    public User getUserInfo(int userId) {
        User user = new User();
        user.setId(userId);
        user.setName("张三");
        user.setAge(24);
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setDesc("用户张三的信息");
        return user;
    }
}
