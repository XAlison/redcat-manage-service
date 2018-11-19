package com.ywsoftware.oa.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 外套异常
 */
public class WrapperAppException extends ApplicationException {

    private final Map<String, Object> data = new HashMap<>();

    public WrapperAppException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getCause().getStackTrace();
    }

    public Map<String, Object> getData() {
        return data;
    }
}
