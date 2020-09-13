package com.hackerstudy.adminclient.pojo;

import lombok.Data;

/**
 * @class: Person
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-18 16:02
 */
@Data
public class Person {
    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }
}
