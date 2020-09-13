package com.hackerstudy.adminclient.exception;

/**
 * @class: RuleException(继承运行时异常)
 * @description: 自定义规则处理异常（spring中只对抛出异常为RuntimeException才会做出事务回滚，
 * 如果是Exception异常是不会回滚的）
 * @author: yangpeng03614
 * @date: 2018-10-24 11:12
 */
public class RuleException extends RuntimeException{
    /** 序列化序号*/
    private static final long serialVersionUID = -8896664116443456374L;

    /** 错误码*/
    private Integer code;

    public RuleException(String message){
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public RuleException(String message, Throwable cause){
        super(message,cause);
    }

    //根异常类（可以存入任何异常）
    public RuleException(Throwable cause){
        super(cause);
    }

    public RuleException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
