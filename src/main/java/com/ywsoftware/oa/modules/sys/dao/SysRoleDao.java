package com.ywsoftware.oa.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ywsoftware.oa.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author xiewl
 * @version 1.0
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
