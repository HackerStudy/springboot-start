package com.hackerstudy.adminclient.service;

import com.hackerstudy.adminclient.pojo.User;

import java.util.List;

/**
 * @class: IMService
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:03
 */
public interface IMService {
    User getUserInfo(int userId);

    List<User> getChatRoomUser();
}
