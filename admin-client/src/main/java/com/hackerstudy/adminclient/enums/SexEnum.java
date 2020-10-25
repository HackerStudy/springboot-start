package com.hackerstudy.adminclient.enums;

/**
 * @class: SexEnum
 * @description: 性别的枚举类
 * @author: HackerStudy
 * @date: 2020-10-25 10:41
 */
public enum  SexEnum {
    MAN(0,"男"),
    WOMAN(1,"女");

    private Integer code;

    private String description;

    SexEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code获取枚举对象
     * @param code
     * @return
     */
    public static SexEnum getEnumByCode(Integer code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode().equals(code)) {
                return sexEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SexEnum{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
