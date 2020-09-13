package com.hackerstudy.adminclient.service.impl;

import com.hackerstudy.adminclient.dao.UserMapper;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @class: IMServiceImpl
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:04
 */
@Service("iMService")
public class IMServiceImpl implements IMService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfo(int userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public List<User> getChatRoomUser() {
        return userMapper.getAllUser();
    }
}
