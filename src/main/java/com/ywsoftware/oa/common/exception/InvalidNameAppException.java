package com.ywsoftware.oa.common.exception;

/**
 * 无效的命名
 */
public class InvalidNameAppException extends ApplicationException {

    public InvalidNameAppException() {
        super("无效的命名");
    }

    public InvalidNameAppException(String message) {
        super(message);
    }

    public InvalidNameAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
