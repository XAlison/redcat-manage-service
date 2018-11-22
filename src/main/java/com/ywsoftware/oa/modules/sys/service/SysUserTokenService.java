package com.ywsoftware.oa.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.ywsoftware.oa.common.utils.Result;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;

/**
 * 用户Token
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

}
