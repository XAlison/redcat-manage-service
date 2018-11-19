package com.ywsoftware.oa.modules.sys.domain.enums;

/**
 * @Description: 登录状态
 * @Date: 2018/11/2 16:50
 */
public enum AccountStateEnum {
    ONE(1, "正常登录"),
    TWO(2, "首次登录"),
    THREE(3, "不同设备"),
    FOUR(4, "不同游览器"),
    FIVE(5, "不同IP"),
    SIX(6, "异地登录"),
    SEVEN(7, "5次密码错误"),
    EIGHT(8, "7天未登录");

    private final int state;
    private final String value;

    AccountStateEnum(int state, String value) {
        this.value = value;
        this.state = state;
    }

    public String getValue() {
        return value;
    }

    public int getState() {
        return state;
    }
}
