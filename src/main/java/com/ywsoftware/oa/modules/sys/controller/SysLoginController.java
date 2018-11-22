package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.common.utils.CookieHelper;
import com.ywsoftware.oa.common.utils.Result;
import com.ywsoftware.oa.modules.sys.service.SysUserTokenService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关
 *
 * @author xiewl
 * @version 1.0
 */
@RestController
public class SysLoginController extends AbstractController {
	@Resource
	private SysUserTokenService sysUserTokenService;

	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public Result logout() {
		sysUserTokenService.logout(getUserId());
		CookieHelper.clearCookie();
		return Result.ok();
	}
	
}
