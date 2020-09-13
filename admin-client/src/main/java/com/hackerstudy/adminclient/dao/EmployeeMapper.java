package com.hackerstudy.adminclient.dao;

import com.hackerstudy.adminclient.common.dao.BaseMapper;
import com.hackerstudy.adminclient.pojo.Employee;

import java.util.List;

/**
 * @class: EmployeeMapper
 * @description: Employee的dao层
 * @author: Administrator
 * @date: 2019-10-24 22:04
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 查询员工列表
     * @return
     */
    List<Employee> getEmployees();
}
