package com.ywsoftware.oa.modules.sys.domain.model;

/**
 * @author yx
 */
public class LoginModel {

    private String phone;
    private String password;
    private String phoneCode;
    private String tenantId;
    private String id;
    private String loginIp;
    private String cityId;
    private String cityName;
    private int multiValidate;

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getMultiValidate() {
        return multiValidate;
    }

    public void setMultiValidate(int multiValidate) {
        this.multiValidate = multiValidate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }
}
