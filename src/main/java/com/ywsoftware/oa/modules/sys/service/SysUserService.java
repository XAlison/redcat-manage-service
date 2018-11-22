package com.ywsoftware.oa.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import java.util.List;

/**
 * 系统用户
 *
 * @author xiewl
 * @version 1.0
 */
public interface SysUserService extends IService<Account> {

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(String userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(String userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	Account queryByUserName(String username);

}
