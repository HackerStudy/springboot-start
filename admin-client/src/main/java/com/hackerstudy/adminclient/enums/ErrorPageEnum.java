package com.hackerstudy.adminclient.enums;

/**
 * @class: ErrorPageEnum
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-10 09:28
 */
public enum ErrorPageEnum {
    ERROR400(400,"thymeleaf/error/400"),
    ERROR401(401,"thymeleaf/error/401"),
    ERROR404(404,"thymeleaf/error/404"),
    ERROR403(403,"thymeleaf/error/403"),
    ERROR500(500,"thymeleaf/error/500");

    //错误码
    private Integer code;

    //自定义错误页面地址
    private String pagePath;

    ErrorPageEnum(Integer code, String pagePath) {
        this.code = code;
        this.pagePath = pagePath;
    }

    public Integer getCode() {
        return code;
    }

    public String getPagePath() {
        return pagePath;
    }

    /**
     * 根据错误码获取枚举对象
     * @param code
     * @return
     */
    public static ErrorPageEnum getErrorPageEnumByCode(Integer code) {
        for (ErrorPageEnum errorPageEnum : ErrorPageEnum.values()) {
            if (errorPageEnum.getCode().equals(code)) {
                return errorPageEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ErrorPageEnum{" +
                "code=" + code +
                ", pagePath='" + pagePath + '\'' +
                '}';
    }
}
