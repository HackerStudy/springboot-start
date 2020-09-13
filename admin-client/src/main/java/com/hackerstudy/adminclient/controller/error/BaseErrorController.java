package com.hackerstudy.adminclient.controller.error;

import com.hackerstudy.adminclient.enums.ErrorPageEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @class: BaseErrorController
 * @description: 统一错误处理controller(错误页面)
 * @author: yangpeng03614
 * @date: 2019-04-10 09:47
 */
@Controller
public class BaseErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,403,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){
            return ErrorPageEnum.getErrorPageEnumByCode(statusCode).getPagePath();
        }else if(statusCode == 404){
            return ErrorPageEnum.getErrorPageEnumByCode(statusCode).getPagePath();
        }else if(statusCode == 403){
            return ErrorPageEnum.getErrorPageEnumByCode(statusCode).getPagePath();
        }else{
            return ErrorPageEnum.getErrorPageEnumByCode(statusCode).getPagePath();
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
