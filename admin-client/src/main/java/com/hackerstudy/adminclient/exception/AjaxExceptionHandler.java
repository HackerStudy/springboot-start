package com.hackerstudy.adminclient.exception;

import com.hackerstudy.adminclient.common.vo.JSONResult;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
public class AjaxExceptionHandler {

//	@ExceptionHandler(value = Exception.class)
	public JSONResult defaultErrorHandler(HttpServletRequest req,
										  Exception e) throws Exception {

		e.printStackTrace();
		return JSONResult.error500(e.getMessage());
	}
}
