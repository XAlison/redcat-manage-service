package com.ywsoftware.oa.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ywsoftware.oa.modules.sys.domain.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author xiewl
 * @version 1.0
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
