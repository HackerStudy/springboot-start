package com.hackerstudy.adminclient.service;

import com.hackerstudy.adminclient.pojo.User;

import javax.jws.WebService;

/**
 * @class: UserService
 * @description: 用户的webservice接口
 * @author: HackerStudy
 * @date: 2020-07-29 21:33
 */
@WebService(name = "UserService",targetNamespace = "http://service.adminclient.hackerstudy.com")
public interface UserService {
    //@WebMethod
    //@WebResult(name = "User")
    //User getUserInfo(@WebParam(name = "userId") int userId);
    User getUserInfo(int userId);
}
