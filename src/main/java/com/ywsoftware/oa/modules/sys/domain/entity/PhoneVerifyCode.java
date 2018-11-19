package com.ywsoftware.oa.modules.sys.domain.entity;

import com.ywsoftware.oa.modules.sys.domain.AggregateRoot;
import com.ywsoftware.oa.modules.sys.domain.EntityImpl;

public class PhoneVerifyCode extends EntityImpl implements AggregateRoot {

    private String phone;

    private String type;

    private String code;

    public PhoneVerifyCode() {
        super();
    }

    public PhoneVerifyCode(String phone, String type, String code) {
        this();
        this.phone = phone;
        this.type = type;
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
