package com.hackerstudy.adminclient.service;

import com.hackerstudy.adminclient.pojo.Employee;

import java.util.List;
import java.util.Map;

/**
 * @class: EmployeesService
 * @description: Employees业务逻辑接口类
 * @author: Administrator
 * @date: 2019-10-24 21:52
 */
public interface EmployeesService {

    /**
     * 获取员工列表
     * @return
     */
    List<Employee> getEmployees();

    /**
     * 根据查询条件查询单个员工
     * @return
     */
    Employee getEmployee(Map<String,Object> queryMap);

    /**
     * 添加员工
     * @return
     */
    Integer addEmployee(Employee employee);

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    Integer updateEmployee(Employee employee);
}
