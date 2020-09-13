package com.hackerstudy.adminclient.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackerstudy.adminclient.enums.JSONResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * 自定义json响应结构
 */
@ApiModel
public class JSONResult<T> implements Serializable {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 错误码 */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /** 提示信息 */
    @ApiModelProperty(value = "提示信息")
    private String msg;

    /** 具体的内容 */
    @ApiModelProperty(value = "返回数据")
    private T data;

    public JSONResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult(Integer code) {
        this.code = code;
    }

    public JSONResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JSONResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public JSONResult(String msg, T data) {
        this.code = code;
        this.data = data;
    }



    public JSONResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        JSONResult<?> JSONResult = (JSONResult<?>) o;
        return Objects.equals(code, JSONResult.code) &&
                Objects.equals(msg, JSONResult.msg) &&
                Objects.equals(data, JSONResult.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, msg, data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        return "JSONResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 判断返回是否成功
     * @return
     */
    @JsonIgnore   //json忽略isSuccess()这个方法(后台推数据到前台的时候,就会把这个给忽略掉)
    public boolean isSuccess() {
        return this.code.equals(JSONResultCodeEnum.SUCCESS.getCode());
    }


    public static <T> JSONResult<T> ok(String msg,T data){
        return new JSONResult<T>(JSONResultCodeEnum.SUCCESS.getCode(),msg,data);
    }

    public static <T> JSONResult<T> ok(T data){
        return new JSONResult<T>(JSONResultCodeEnum.SUCCESS.getCode(),JSONResultCodeEnum.SUCCESS.getDesc(),data);
    }
    
    public static <T> JSONResult<T> error(String msg,T data){
        return new JSONResult<T>(JSONResultCodeEnum.ERROR.getCode(),msg,data);
    }

    public static <T> JSONResult<T> error(Integer code,String msg,T data){
        return new JSONResult<T>(code,msg,data);
    }

    public static <T> JSONResult<T> error(Integer code,String msg){
        return new JSONResult<T>(code,msg);
    }

    public static <T> JSONResult<T> error(){
        return new JSONResult<T>(JSONResultCodeEnum.ERROR.getCode(),JSONResultCodeEnum.ERROR.getDesc());
    }

    public static <T> JSONResult<T> error(T data){
        return new JSONResult<T>(JSONResultCodeEnum.ERROR.getCode(),JSONResultCodeEnum.ERROR.getDesc(),data);
    }

    public static <T> JSONResult<T> error(String msg){
        return new JSONResult<T>(JSONResultCodeEnum.ERROR.getCode(),msg);
    }

    public static <T> JSONResult<T> error500(String msg){
        return new JSONResult<T>(JSONResultCodeEnum.ERROR500.getCode(),msg);
    }
}
