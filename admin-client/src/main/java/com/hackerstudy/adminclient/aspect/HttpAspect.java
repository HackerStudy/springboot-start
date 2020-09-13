package com.hackerstudy.adminclient.aspect;

import com.alibaba.fastjson.JSON;
import com.hackerstudy.adminclient.exception.RuleException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect //标注该类的是aop类
@Component
/**
 * http请求前后的一些数据的日志打印
 */
public class HttpAspect {

    /** 序列化日志文件 */
    private static  final Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    /** com.hackerstudy.adminclient.controller.user.UserController包下面的公共方法需要被执行"AOP" */
    @Pointcut("execution(public * com.hackerstudy.adminclient.controller.user.UserController.*(..))")
    public void log(){
    }

    /** com.hackerstudy.adminclient.controller包及其子包的方法需要被执行"AOP" */
    @Pointcut("execution(* com.hackerstudy.adminclient.controller.*.*(..))")
    public void logController(){
    }

    /** 所以的方法都需要被执行"AOP" */
    @Pointcut("execution(* *(..))")
    public void logAll(){

    }

    /**
     * 前置通知，方法调用前被调用
     * 请求前的提示语句
     */
    @Before("log()")
    public void doBeforeLog(){
        logger.info("请求前");
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * 请求执行后的提示语句
     */
    @After("log()")
    public void doAfterLog(){
        logger.info("请求后");
    }

    /**
     * 获取请求的url,method,ip,class_method,args
     * @param joinPoint
     */
    @Before("log()")
    public void doBeforeHttpRequestLog(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<>();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(joinPoint.getArgs().length > 0) {
            System.out.println("请求的参数信息为："+str);
        }
        //url
        logger.info("url={}",request.getRequestURI());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
        //aop代理类的信息
        logger.info("aop代理类的信息",joinPoint.getThis());
        //代理的目标对象
        logger.info("代理的目标对象",joinPoint.getTarget());
        //AOP代理类的类（class）信息
        logger.info("AOP代理类的类（class）信息",joinPoint.getSignature().getDeclaringType());
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     *      returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * 输出请求后的返回结果
     * @param object
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint,Object object){
        logger.info("response={}",object.toString());
    }

    @AfterReturning(value = "log()",returning = "keys",argNames = "keys")
    public void doAfterReturningAdvice2(String keys){
        logger.info("第二个后置返回通知的返回值：",keys);
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "log()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        logger.info("className:{}",joinPoint.getSignature().getName());
        if(exception instanceof RuleException){
            logger.info("发生自定义异常");
        }else if(exception instanceof Exception){
            logger.info("发生系统异常");
        }
    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execution(* com.hackerstudy.adminclient.controller.*.testAround*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("环绕通知的目标方法名：{}",proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            logger.info("方法环绕proceed的结果：{}",obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
