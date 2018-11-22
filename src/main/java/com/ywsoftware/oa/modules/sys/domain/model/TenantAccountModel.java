package com.ywsoftware.oa.modules.sys.domain.model;

public class TenantAccountModel {

    private String accountId;
    private String tenantName;
    private boolean isFirstLogin;
    private int multiValidate;

    public int getMultiValidate() {
        return multiValidate;
    }

    public void setMultiValidate(int multiValidate) {
        this.multiValidate = multiValidate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}
