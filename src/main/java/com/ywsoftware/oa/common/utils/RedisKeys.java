package com.ywsoftware.oa.common.utils;

/**
 * Redis所有Keys
 *
 * @author xiewl
 * @version 1.0
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
