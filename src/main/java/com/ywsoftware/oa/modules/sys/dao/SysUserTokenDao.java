package com.ywsoftware.oa.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author xiewl
 * @version 1.0
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
