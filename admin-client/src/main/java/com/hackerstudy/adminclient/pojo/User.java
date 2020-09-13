package com.hackerstudy.adminclient.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @class: User
 * @description: Jackson的基本演绎法
 * @author: yangpeng03614
 * @date: 2019-04-04 14:54
 */
@ApiModel
public class User {
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户姓名")
    private String name;
    //返回json时忽略password
//    @JsonIgnore

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;
    //对Date时间进行格式化，pattern格式化表达式、locale国家、timezone时区
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")

    @ApiModelProperty(value = "用户生日")
    private Date birthday;
    //当desc为空时不返回给前端

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "用户的描述")
    private String desc;

    public User(Integer id, String name, String password, Integer age, Date birthday, String desc) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.birthday = birthday;
        this.desc = desc;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", desc='" + desc + '\'' +
                '}';
    }
}
