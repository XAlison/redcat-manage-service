package com.ywsoftware.oa.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ywsoftware.oa.common.utils.Result;
import com.ywsoftware.oa.modules.sys.dao.SysUserTokenDao;
import com.ywsoftware.oa.modules.sys.domain.entity.SysUserTokenEntity;
import com.ywsoftware.oa.modules.sys.oauth2.TokenGenerator;
import com.ywsoftware.oa.modules.sys.service.SysUserTokenService;
import java.util.Date;
import org.springframework.stereotype.Service;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {

	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public Result createToken(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();

		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.selectById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.insert(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		return Result.ok()
				.put("token", token)
				.put("expire", EXPIRE);
	}

	@Override
	public void logout(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}
}
