package com.ywsoftware.oa.modules.sys.service;

import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;
import java.util.Set;

/**
 * shiro相关接口
 *
 * @author xiewl
 * @version 1.0
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    Account queryUser(String userId);
}
