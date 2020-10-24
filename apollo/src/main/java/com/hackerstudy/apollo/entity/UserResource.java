package com.hackerstudy.apollo.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @class: UserResource
 * @description: 用户信息的配置类
 * @author: HackerStudy
 * @date: 2020-10-24 18:00
 */
@Data
@Component
public class UserResource {
    @Value("${user.id:李四}")
    private Integer id;

    @Value("${user.name}")
    private String name;

    @Value("${user.sex}")
    private String sex;

    @Value("${user.address}")
    private String address;
}
