package com.hackerstudy.adminclient.pojo;

import javax.persistence.*;

public class Role {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 功能权限
     */
    private String authorization;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取功能权限
     *
     * @return authorization - 功能权限
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     * 设置功能权限
     *
     * @param authorization 功能权限
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}