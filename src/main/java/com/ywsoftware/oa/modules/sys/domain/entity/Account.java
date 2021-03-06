package com.ywsoftware.oa.modules.sys.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.ywsoftware.oa.modules.sys.domain.AggregateRoot;
import com.ywsoftware.oa.modules.sys.domain.EntityImpl;
import java.util.List;

@TableName("user_account")
public class Account extends EntityImpl implements AggregateRoot {

    public static final String DEFAULT_PASSWORD = "888888";

    private String name;
    private String passwordHash;
    private String managePasswordHash;
    private String securityPasswordHash;
    private String phone;
    private String email;
    private Boolean isEnabled;
    private boolean isManager;
    private String lastLoginTime;
    private String tenantId;
    private List<Long> roleIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return phone;
    }

    public void setUsername(String username) {
        this.phone = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getManagePasswordHash() {
        return managePasswordHash;
    }

    public void setManagePasswordHash(String managePasswordHash) {
        this.managePasswordHash = managePasswordHash;
    }

    public String getSecurityPasswordHash() {
        return securityPasswordHash;
    }

    public void setSecurityPasswordHash(String securityPasswordHash) {
        this.securityPasswordHash = securityPasswordHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public boolean getManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
