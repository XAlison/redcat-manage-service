package com.ywsoftware.oa.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ywsoftware.oa.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author xiewl
 * @version 1.0
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
