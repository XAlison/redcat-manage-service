package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.common.utils.PageUtils;
import com.ywsoftware.oa.common.utils.Result;
import com.ywsoftware.oa.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 系统日志
 *
 * @author xiewl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

	@Resource
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:log:list")
	public Result list(@RequestParam Map<String, Object> params){
		PageUtils page = sysLogService.queryPage(params);

		return Result.ok().put("page", page);
	}
	
}
