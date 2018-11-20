package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.modules.sys.domain.PaginatedFilter;
import com.ywsoftware.oa.modules.sys.domain.PaginatedItems;
import com.ywsoftware.oa.modules.sys.domain.entity.AccountLogs;
import com.ywsoftware.oa.modules.sys.domain.enums.AccountStateEnum;
import com.ywsoftware.oa.modules.sys.service.mapper.AccountLogsMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LogsService
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2018/11/2 13:36
 * @Version 1.0
 */
@Service
public class AccountLogsService {

    private final AccountLogsMapper logsMapper;

    public AccountLogsService(AccountLogsMapper mapper) {
        this.logsMapper = mapper;
    }

    /**
     * @Description: 保存日志
     * @Date: 2018/11/5 10:31
     * @Version 1.0
     */
    public int saveUserLog(String userId, String remark,
                           String loginIp, String cityId,
                           String cityName, HttpServletRequest request) throws Exception {
        return logsMapper.saveUserLog(this.getUserLoginLog(userId, remark,
                loginIp, cityId, cityName, request));
    }

    /**
     * @Description: 构建日志对象
     * @Date: 2018/11/2 17:13
     * @Version 1.0
     */
    public AccountLogs getUserLoginLog(String userId, String remark, String loginIp, String cityId, String cityName, HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        // 解析agent
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        // 获取浏览器对象
        Browser browser = userAgent.getBrowser();
        // 获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 构建对象
        AccountLogs log = AccountLogs.builder()
                .userId(userId)
                .loginTime(new Date())
                .browserName(browser.getName())
                .browserType(String.valueOf(browser.getBrowserType()))
                .browserVersion(String.valueOf(userAgent.getBrowserVersion()))
                .deviceType(String.valueOf(operatingSystem.getDeviceType()))
                .systemName(operatingSystem.getName())
                .systemGroup(String.valueOf(operatingSystem.getGroup()))
                .loginIp(loginIp)
                .cityId(cityId)
                .cityName(cityName)
                .remark(remark)
                .otherInfo(String.valueOf(operatingSystem.getManufacturer()))
                .build();
        return log;
    }

    /**
     * @Description: 二次校验登录(可能存在多种情况, 任何一种不满足就行)
     * @Date: 2018/11/2 17:04
     * @Version 1.0
     */
    public int secondValidate(AccountLogs log) {
        // 获取最近登录记录
        AccountLogs userLog = logsMapper.findLastUserLog(log.getUserId());
        // 首次登录
        AccountStateEnum stateEnum = AccountStateEnum.ONE;
        if (userLog == null) {
            stateEnum = AccountStateEnum.TWO;
        }
        if (userLog != null) {
            //  不同设备登录,比较当前登录系统以及设备
            if (!(Objects.equals(userLog.getDeviceType(), log.getDeviceType())
                    && Objects.equals(log.getSystemName(), userLog.getSystemName()))) {
                stateEnum = AccountStateEnum.THREE;
            }
            // 不同游览器
            if (!(Objects.equals(log.getBrowserName(),
                    userLog.getBrowserName())
                    && Objects.equals(userLog.getBrowserVersion(),
                    log.getBrowserVersion()))) {
                stateEnum = AccountStateEnum.FOUR;
            }
            // 不同IP
            if (!Objects.equals(userLog.getLoginIp(), log.getLoginIp())) {
                stateEnum = AccountStateEnum.FIVE;
            }
            // 30不在常驻城市登录(同一个城市三次以上为常驻城市)
            int cityCount = logsMapper.findLoginCityCount(log);
            if (cityCount < 1) {
                stateEnum = AccountStateEnum.SIX;
            }
            // 五次账号密码错误
            int passCount = logsMapper.findLoginPassCount(log.getUserId());
            if (passCount >= 5) {
                stateEnum = AccountStateEnum.SEVEN;
            }
            // 7天未登录
            int dayCount = logsMapper.findLoginDayCount(log.getUserId());
            if (dayCount < 1) {
                stateEnum = AccountStateEnum.EIGHT;
            }
        }
        return stateEnum.getState();
    }

    public PaginatedItems<AccountLogs> getLoginLogs(PaginatedFilter filter) {
        List<AccountLogs> loginLogs = logsMapper.getLoginLogs(filter);
        Long totalCount = logsMapper.getLoginLogsCount(filter);
        return new PaginatedItems<>(loginLogs, totalCount);
    }
}
