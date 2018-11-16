package com.ywsoftware.oa.common.validator;

import com.ywsoftware.oa.common.exception.GlobalException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author xiewl
 * @version 1.0
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new GlobalException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new GlobalException(message);
        }
    }
}
