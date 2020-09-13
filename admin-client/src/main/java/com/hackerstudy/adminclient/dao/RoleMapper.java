package com.hackerstudy.adminclient.dao;

import com.hackerstudy.adminclient.common.dao.BaseMapper;
import com.hackerstudy.adminclient.pojo.Role;

public interface RoleMapper extends BaseMapper<Role> {
    int saveRole(Role role);
}