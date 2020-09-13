package com.hackerstudy.adminclient.service.impl;

import com.hackerstudy.adminclient.dao.EmployeeMapper;
import com.hackerstudy.adminclient.pojo.Employee;
import com.hackerstudy.adminclient.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @class: EmployeesServiceImpl
 * @description: Employees处理的业务逻辑实现类
 * @author: Administrator
 * @date: 2019-10-24 22:03
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Override
    public Employee getEmployee(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public Integer addEmployee(Employee employee) {
        return null;
    }

    @Override
    public Integer updateEmployee(Employee employee) {
        return null;
    }
}
