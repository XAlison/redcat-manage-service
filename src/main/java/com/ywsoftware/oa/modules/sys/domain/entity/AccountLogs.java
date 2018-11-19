package com.ywsoftware.oa.modules.sys.domain.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserLogs
 * @Description: 登录账号日志
 * @Date: 2018/11/2 11:04
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLogs {
    private long id;
    /**
     * 登录名
     */
    private String userId;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 游览器名
     */
    private String browserName;
    /**
     * 游览器类型
     */
    private String browserType;
    /**
     * 游览器版本
     */
    private String browserVersion;
    /**
     * 设备名称
     */
    private String deviceType;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 操作系统家族
     */
    private String systemGroup;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录城市id
     */
    private String cityId;
    /**
     * 登录城市
     */
    private String cityName;
    /**
     * 其它信息
     */
    private String otherInfo;
}
