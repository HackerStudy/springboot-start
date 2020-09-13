package com.hackerstudy.adminclient.controller.employees;

import com.hackerstudy.adminclient.pojo.Employee;
import com.hackerstudy.adminclient.pojo.User;
import com.hackerstudy.adminclient.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @class: EmployeesController
 * @description: 员工信息的接口
 * @author: Administrator
 * @date: 2019-10-24 20:18
 */
@Controller
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login(){
        return "/thymeleaf/employees/login";
    }

    /**
     * 处理登录请求
     * @return
     */
    @PostMapping("/employees/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        ModelMap map, HttpSession session){
       if("admin".equals(username)&& "123456".equals(password)){
           //重定向防止重复提交
           User user = new User();
           user.setId(1);
           user.setName(username);
           user.setPassword(password);
           session.setAttribute("userSession",user);
           session.setMaxInactiveInterval(30 * 60);
           //return "redirect:/main";
           return "/thymeleaf/employees/dashboard";
        }else{
           map.put("msg","账号密码错误,请重新输入");
           return "/thymeleaf/employees/login";
       }
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @RequestMapping("/employees/signOut")
    public String signOut(HttpSession session){
        session.removeAttribute("userSession");
        return "redirect:/login";
    }

    /**
     * 获取员工列表
     * @return
     */
    @RequestMapping("/employees/employees")
    public String employees(ModelMap map){
        List<Employee> employeeList = employeesService.getEmployees();
        map.put("employeeList",employeeList);
        return "/thymeleaf/employees/list";
    }

}
