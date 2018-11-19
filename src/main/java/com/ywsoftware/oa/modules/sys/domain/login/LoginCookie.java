package com.ywsoftware.oa.modules.sys.domain.login;

/**
 * @author yx
 */
public class LoginCookie {

    private String userId;
    private String name;
    private String phone;
    private String tenantId;
    private boolean isManager;

    public LoginCookie() {
    }

    public LoginCookie(String userId, String name, String phone, String tenantId, Boolean isManager) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.tenantId = tenantId;
        this.isManager = isManager;
    }

    public boolean getManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
