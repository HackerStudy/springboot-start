package com.hackerstudy.adminclient.config;

import com.hackerstudy.adminclient.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Locale;

/**
 * @class: MyAPPConfig
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-18 16:01
 */
@Configuration
public class MyAPPConfig {

    /**
     * person对象的bean
     * @return person
     */
    @Bean
    public Person person() {
        return new Person(1, "张三");
    }

    /**
     * 注入websocket的bean
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    /**
     * @class: MyViewResolver
     * @description: 自定义springmvc的视图解析器
     * @author: yangpeng1
     * @date: 2019-10-20 23:18
     */
    private class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
