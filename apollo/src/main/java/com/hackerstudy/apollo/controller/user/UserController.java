package com.hackerstudy.apollo.controller.user;

import com.hackerstudy.apollo.entity.UrlResource;
import com.hackerstudy.apollo.entity.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @class: UserController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-04 14:57
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserResource userResource;

    @Autowired
    UrlResource urlResource;

    @GetMapping("/user/userResource/{userId}")
    public String userResource(@PathVariable Integer userId) {
        log.info("userId:"+userId+",userResource:"+userResource.toString());
        return userResource.toString();
    }

    @GetMapping("/user/urlResource")
    public String urlResource() {
        log.info("urlResource:"+urlResource.toString());
        return urlResource.toString();
    }
}
