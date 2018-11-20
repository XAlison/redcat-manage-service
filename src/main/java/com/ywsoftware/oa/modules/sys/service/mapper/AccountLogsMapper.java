package com.ywsoftware.oa.modules.sys.service.mapper;

import com.ywsoftware.oa.modules.sys.domain.PaginatedFilter;
import com.ywsoftware.oa.modules.sys.domain.entity.AccountLogs;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountLogsMapper {
    int saveUserLog(AccountLogs log);

    AccountLogs findLastUserLog(String userId);

    int findLoginCityCount(AccountLogs log);

    int findLoginDayCount(String userId);

    int findLoginPassCount(String userId);

    /**
     * 登录日志列表
     */
    List<AccountLogs> getLoginLogs(PaginatedFilter filter);

    Long getLoginLogsCount(PaginatedFilter filter);
}

