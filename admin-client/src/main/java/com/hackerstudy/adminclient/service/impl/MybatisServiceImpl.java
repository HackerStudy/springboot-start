package com.hackerstudy.adminclient.service.impl;

import com.github.pagehelper.PageHelper;
import com.hackerstudy.adminclient.dao.RoleMapper;
import com.hackerstudy.adminclient.pojo.Role;
import com.hackerstudy.adminclient.service.MybatisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @class: MybatisServiceImpl
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-10 17:17
 */
@Service("mybatisService")
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int saveRole(Role role) throws Exception{
        return roleMapper.saveRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> queryAllRoleList() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> queryRoleListPage(Integer page, Integer pageSize, String term) {
        //开始分页
        PageHelper.startPage(page,pageSize);
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(term)){
            criteria.andLike("authorization","%"+term+"%");
        }
        example.orderBy("id").desc();
        return roleMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateRoleTransactional(Role role) {
        int i = roleMapper.updateByPrimaryKeySelective(role);
        int a = 1/0;
        return i;
    }
}
