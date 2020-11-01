package com.hackerstudy.adminclient.common.annotation;

import java.lang.annotation.*;

/**
 * @class: ResponseResult
 * @description: 检测接口是否需要返回值的包装类
 * @author: hacker
 * @date: 2020-10-31 20:41
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
