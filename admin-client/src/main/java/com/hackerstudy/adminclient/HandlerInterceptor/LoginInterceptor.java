package com.hackerstudy.adminclient.HandlerInterceptor;

import com.hackerstudy.adminclient.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @class: LoginInterceptor
 * @description:  登录拦截器
 * @author: Administrator
 * @date: 2019-10-24 21:20
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        User user = (User) request.getSession().getAttribute("userSession");
        if(null==user){
            request.setAttribute("msg","没有权限请先登录");
//            request.getRequestDispatcher("/login").forward(request,response);
            response.sendRedirect("/login");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
