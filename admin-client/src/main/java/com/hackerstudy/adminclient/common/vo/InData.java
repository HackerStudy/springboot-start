package com.hackerstudy.adminclient.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 封装前端传过来的数据
 */
@ApiModel
public class InData implements Serializable{

    private static final long serialVersionUID = 4956832764901065172L;//序列化id用来验证版本的一致性

    /**
     * 请求的标记
     */
    @ApiModelProperty(value = "标识")
    private String sign;
    /**
     * 单行请求参数
     */
    @ApiModelProperty(value = "对象")
    private Map<String,Object> inMap;
    /**
     * 多行请求参数
     */
    @ApiModelProperty(value = "数组")
    private List<Map<String,Object>> inList;

    public InData(String sign, Map<String, Object> inMap, List<Map<String, Object>> inList) {
        this.sign = sign;
        this.inMap = inMap;
        this.inList = inList;
    }

    public InData() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, Object> getInMap() {
        return inMap;
    }

    public void setInMap(Map<String, Object> inMap) {
        this.inMap = inMap;
    }

    public List<Map<String, Object>> getInList() {
        return inList;
    }

    public void setInList(List<Map<String, Object>> inList) {
        this.inList = inList;
    }

    @Override
    public String toString() {
        return "InData{" +
                "sign='" + sign + '\'' +
                ", inMap=" + inMap +
                ", inList=" + inList +
                '}';
    }
}

