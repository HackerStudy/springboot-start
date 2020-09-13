package com.hackerstudy.adminclient.exception;

import com.hackerstudy.adminclient.common.vo.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @class: ExceptionHandle
 * @description: 统一异常处理类
 * @author: yangpeng03614
 * @date: 2018-10-24 09:51
 */
//基于@ControllerAdvice注解的全局异常统一处理只能针对于Controller层的异常，意思是只能捕获到Controller层的异常，在service层或者其他层面的异常都不能捕获。
@ControllerAdvice //controller 增强器
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 处理exception异常，并打印异常输出
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResult<?> handleException(Exception e){
        if(e instanceof RuleException){
            return JSONResult.error(((RuleException) e).getCode(),e.getMessage());
        }
        logger.error("【系统异常】捕获到Exception异常",e);
        return JSONResult.error500(e.getMessage());
    }


    /**
     * 处理RuleException异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuleException.class)
    @ResponseBody
    public JSONResult<?> handleRuleException(RuleException e){
        logger.error("【自定义异常】捕获到RuleException异常",e);
        return JSONResult.error(e.getCode(),e.getMessage());
    }

    /**
     * 1.ajax请求则返回500的错误信息
     * 2.不是ajax请求则跳转到错误页面
     * @param reqest
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = Exception.class)
//    public Object errorHandler(HttpServletRequest request,
//                               HttpServletResponse response, Exception e) throws Exception {
//
//        e.printStackTrace();
//
//        if (isAjax(request)) {
//            return JSONResult.error500(e.getMessage());
//        } else {
//            ModelAndView mav = new ModelAndView();
//            mav.addObject("exception", e);
//            mav.addObject("url", request.getRequestURL());
//            mav.setViewName("thymeleaf/error/500");
//            return mav;
//        }
//    }

    /**
     *
     * @Title: IMoocExceptionHandler.java
     * @Package com.imooc.exception
     * @Description: 判断是否是ajax请求
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author leechenxiang
     * @date 2017年12月3日 下午1:40:39
     * @version V1.0
     */
    public static boolean isAjax(HttpServletRequest httpRequest){
        return  (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( httpRequest.getHeader("X-Requested-With").toString()) );
    }
}
