package com.ywsoftware.oa.modules.sys.model;

/**
 * @ClassName: VerifyCodeModel
 * @Description: 参数model
 * @Author: xiewl
 * @Date: 2018/8/23 21:23
 * @Version 1.0
 */
public class VerifyCodeModel {
    private String phoneNum;
    private String picCode;
    private String phoneCode;
    private String type;
    private String message;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
