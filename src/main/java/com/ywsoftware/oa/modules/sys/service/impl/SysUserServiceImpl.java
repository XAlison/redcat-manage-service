package com.ywsoftware.oa.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ywsoftware.oa.modules.sys.dao.SysUserDao;
import com.ywsoftware.oa.modules.sys.domain.entity.Account;
import com.ywsoftware.oa.modules.sys.service.SysUserService;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 * 系统用户
 *
 * @author xiewl
 * @version 1.0
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, Account> implements SysUserService {

	@Override
	public List<String> queryAllPerms(String userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(String userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public Account queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}


}
