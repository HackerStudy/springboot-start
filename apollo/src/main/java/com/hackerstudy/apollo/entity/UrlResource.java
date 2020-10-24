package com.hackerstudy.apollo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @class: UrlResource
 * @description:
 * @author: HackerStudy
 * @date: 2020-10-24 18:26
 */
@Data
@Component
@ConfigurationProperties(prefix = "url")
public class UrlResource {
    private String adminServerUrl;
}
