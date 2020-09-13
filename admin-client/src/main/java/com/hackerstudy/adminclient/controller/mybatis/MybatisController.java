package com.hackerstudy.adminclient.controller.mybatis;

import com.hackerstudy.adminclient.common.vo.JSONResult;
import com.hackerstudy.adminclient.pojo.Role;
import com.hackerstudy.adminclient.service.MybatisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @class: MybatisController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-10 17:09
 */
@RestController
@RequestMapping("mybatis")
public class MybatisController {

    private static final Logger logger = LoggerFactory.getLogger(MybatisController.class);

    @Autowired
    MybatisService mybatisService;

    @RequestMapping("/saveRole")
    public JSONResult<?> saveRole() throws Exception {
        Role role = new Role();
        role.setName("admin");
        role.setAuthorization("只能对用户进行操作");
        return JSONResult.ok("添加成功",mybatisService.saveRole(role));
    }

    @RequestMapping("/updateRole")
    public JSONResult<?> updateRole() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("superadmin");
        role.setAuthorization("全部权限");
        return JSONResult.ok("修改成功",mybatisService.updateRole(role));
    }

    @RequestMapping("/deleteRole")
    public JSONResult<?> deleteRole() throws Exception {
        return JSONResult.ok("删除成功",mybatisService.deleteRole(2));
    }

    @RequestMapping("/queryAllRole")
    public JSONResult<?> queryAllRole(){
        return JSONResult.ok("查询成功",mybatisService.queryAllRoleList());
    }

    @RequestMapping("/queryRoleListPage")
    public JSONResult<?> queryRoleListPage(Integer page,Integer pageSize,String term){
        if(page==null){
            page = 1;
        }
        List<Role> roleList = mybatisService.queryRoleListPage(page,pageSize,term);
        return JSONResult.ok("查询成功",roleList);
    }

    @RequestMapping("/updateRoleTransactional")
    public JSONResult<?> updateRoleTransactional(){
        Role role = new Role();
        role.setId(3);
        role.setAuthorization("只有对于用户的操作权限");
        int i= mybatisService.updateRoleTransactional(role);
        return JSONResult.ok("修改成功（事务管理）",i);
    }

}
