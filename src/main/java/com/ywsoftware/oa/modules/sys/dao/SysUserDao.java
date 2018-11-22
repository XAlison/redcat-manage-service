package com.ywsoftware.oa.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author xiewl
 * @version 1.0
 */
@Mapper
public interface SysUserDao extends BaseMapper<Account> {
	
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
