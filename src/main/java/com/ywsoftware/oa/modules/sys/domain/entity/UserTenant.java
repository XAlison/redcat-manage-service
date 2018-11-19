package com.ywsoftware.oa.modules.sys.domain.entity;

import lombok.Data;

@Data
public class UserTenant {

    private String id;
    // 租户title（原租户名（公司））
    private String tenantTitle;
    // 租户名称
    private String tenantName;
    // 联系人
    private String contacts;
    // 联系电话
    private String telephone;
    // 联系地址
    private String contactsAddress;
    // 是否生效
    private Boolean isEffective;
    // 配色
    private String matchColors;
    // 创建时间
    private java.sql.Timestamp createTime;
    // 备注
    private String remark;
    // 状态（软删）
    private Boolean status;
    // LOGO
    private String logo;
    // 用户名
    private String name;
    // 登入名
    private String loginName;
    // 邮箱
    private String email;
}
