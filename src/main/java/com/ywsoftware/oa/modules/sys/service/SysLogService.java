package com.ywsoftware.oa.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.ywsoftware.oa.common.utils.PageUtils;
import com.ywsoftware.oa.modules.sys.domain.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author xiewl
 * @version 1.0
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
