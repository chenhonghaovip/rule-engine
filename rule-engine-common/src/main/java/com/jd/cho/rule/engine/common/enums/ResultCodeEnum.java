package com.jd.cho.rule.engine.common.enums;

/**
 * Description: Enum Demo
 *
 * @author DongBoot
 */
public enum ResultCodeEnum {
    /**
     * 0: 成功
     */
    SUCCESS("0", "Ok"),

    ;

    private final String code;

    /**
     * 结果code对应的message
     */

    private final String message;


    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static boolean isSuccess(String code) {
        return SUCCESS.getCode().equals(code);
    }

}

