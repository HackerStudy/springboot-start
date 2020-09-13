package com.hackerstudy.adminclient.service;

import com.hackerstudy.adminclient.pojo.Role;

import java.util.List;

/**
 * @class: MybatisService
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-10 17:18
 */
public interface MybatisService{

    int saveRole(Role role) throws Exception;

    int updateRole(Role role);

    int deleteRole(Integer id);

    List<Role> queryAllRoleList();

    List<Role> queryRoleListPage(Integer page,Integer pageSize,String term);

    int updateRoleTransactional(Role role);
}
