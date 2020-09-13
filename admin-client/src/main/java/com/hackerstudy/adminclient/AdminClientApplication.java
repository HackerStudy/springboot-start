package com.hackerstudy.adminclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//开启事务管理
@EnableTransactionManagement
//开启定时任务
//@EnableScheduling
//扫描mybatis mapper 包的路径
@MapperScan("com.hackerstudy.adminclient.dao")
public class AdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}

}
