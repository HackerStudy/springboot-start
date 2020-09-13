package com.hackerstudy.adminclient.dao;

import com.hackerstudy.adminclient.pojo.User;

import java.util.List;

/**
 * @class: UserMapper
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:07
 */
public interface UserMapper {
    User getUserInfo(Integer id);

    List<User> getAllUser();
}
