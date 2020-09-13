package com.hackerstudy.adminclient.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @class: Resource
 * @description: 读取静态资源文件
 * @author: yangpeng03614
 * @date: 2019-04-09 10:01
 */
@Configuration //标识要引用资源文件
@ConfigurationProperties(prefix = "com.hackerstudy.adminclient") //前缀
@PropertySource(value = "classpath:resource.properties") //资源文件的路径（打包后resource下的文件都在classpath里面）
public class Resource {
    private String name;
    private String website;
    private String language;

    public Resource(String name, String website, String language) {
        this.name = name;
        this.website = website;
        this.language = language;
    }

    public Resource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
