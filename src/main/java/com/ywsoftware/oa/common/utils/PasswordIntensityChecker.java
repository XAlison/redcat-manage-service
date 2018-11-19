package com.ywsoftware.oa.common.utils;

/**
 * 密码强度校验相关方法
 *
 * @author yx
 */
public class PasswordIntensityChecker {

    /**
     * 6-20位数字单词大小写正则校验
     */
    private static final String NUM_CHAR_UP_LOW = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,20}$";

    /**
     * 包含数字正则
     */
    private static final String NUM_CHAR_SIGNAL = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$";

    /**
     * 6-20位数字单词大小写正则校验
     *
     * @param password 密码
     * @return true 满足条件
     */
    public static boolean includeNumChar(String password) {
        return password != null && password.matches(NUM_CHAR_UP_LOW);
    }

    /**
     * 6-20位密码，包含字符英文数字其中两种
     * @param password 密码
     * @return true 满足条件
     */
    public static boolean includeNumCharSignal(String password) {
        return password != null && password.matches(NUM_CHAR_SIGNAL);
    }

}
