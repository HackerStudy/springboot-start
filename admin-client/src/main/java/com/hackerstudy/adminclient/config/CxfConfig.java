package com.hackerstudy.adminclient.config;

import com.hackerstudy.adminclient.service.UserService;
import com.hackerstudy.adminclient.service.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @class: CxfConfig
 * @description: webservice服务端发布配置类
 * @author: HackerStudy
 * @date: 2020-07-29 21:47
 */
@Configuration
public class CxfConfig {

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        //创建服务并指定服务名称
        return new ServletRegistrationBean(new CXFServlet(),"/ws/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    /**
     * 注册WebServiceDemoService接口到webservice服务
     * @return
     */
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),userService());
        endpoint.publish("/userService");
        return endpoint;
    }
}
