package com.hackerstudy.adminclient.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @class: Swagger2Config
 * @description:
 * @author: 17911
 * @date: 2020-02-29 11:43
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurationSupport {
    //api接口包扫描路径
    //public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.hackerstudy.adminclient";​
    private static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //接口文档的基本信息
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //方法需要有ApiOperation注解才能生存接口文档
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求(路径使用any风格)
                .build()
                .securitySchemes(security());//如何保护我们的Api，有三种验证（ApiKey, BasicAuth, OAuth）
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("adminclient") //设置文档的标题
                .description("adminclient API 接口文档") // 设置文档的描述
                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
                .termsOfServiceUrl("http://localhost:8083") // 设置文档的License信息->1.3 License information
                .build();
    }

    private List<ApiKey> security() {
        ArrayList<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("token", "token", "header"));
        return apiKeys;
    }

    /**
     * 解决swagger2 404问题(加上静态资源映射)
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
