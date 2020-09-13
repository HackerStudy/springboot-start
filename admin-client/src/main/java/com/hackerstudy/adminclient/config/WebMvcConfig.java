package com.hackerstudy.adminclient.config;

import com.hackerstudy.adminclient.HandlerInterceptor.OneInterceptor;
import com.hackerstudy.adminclient.HandlerInterceptor.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用WebMvcConfigurerAdapter可以来拓展springmvc的功能
 */
//全面接管springmvc的配置（类似以前的ssm中的springmvc配置文件配置）
//@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 自定义视图映射
	 * @param registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		super.addViewControllers(registry);
        //将thymeleaf_index请求解析到thymeleaf的主页
		registry.addViewController("/thymeleafIndex")
                .setViewName("/thymeleaf/thymeleaf_index");
		registry.addViewController("/main")
				.setViewName("/thymeleaf/employees/dashboard");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**")
													 .addPathPatterns("/one/**");
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		//registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/employees/login","swagger-ui.html");
	}

}
