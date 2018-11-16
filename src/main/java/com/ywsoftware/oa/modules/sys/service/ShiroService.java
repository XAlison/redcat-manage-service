package com.ywsoftware.oa.modules.sys.service;

import com.ywsoftware.oa.modules.sys.entity.SysUserEntity;
import com.ywsoftware.oa.modules.sys.entity.SysUserTokenEntity;

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
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
