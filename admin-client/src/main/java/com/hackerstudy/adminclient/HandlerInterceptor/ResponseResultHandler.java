package com.hackerstudy.adminclient.HandlerInterceptor;

import com.hackerstudy.adminclient.common.annotation.ResponseResult;
import com.hackerstudy.adminclient.common.vo.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @class: ResponseResultHandler
 * @description: ResponseResult的处理器
 * @author: HackerStudy
 * @date: 2020-11-01 12:18
 */
@Slf4j
@ControllerAdvice //@ControllerAdvice注解是Spring3.2中新增的注解，学名是Controller增强器，作用是给Controller控制器添加统一的操作或处理。
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    //标记名称
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //判断请求，是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn == null? false: true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进入返回体 重写格式 处理中 ....");
        if(o instanceof Exception){
            log.info("返回值异常 做包装处理中 ....");
            Exception exception = (Exception) o;
            return JSONResult.error(500,exception.getMessage());
        }
        return JSONResult.ok(o);
    }
}
