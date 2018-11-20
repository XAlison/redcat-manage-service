package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.common.utils.PageUtils;
import com.ywsoftware.oa.common.utils.Result;
import com.ywsoftware.oa.modules.sys.domain.PaginatedFilter;
import com.ywsoftware.oa.modules.sys.domain.PaginatedItems;
import com.ywsoftware.oa.modules.sys.domain.entity.AccountLogs;
import com.ywsoftware.oa.modules.sys.service.SysLogService;
import com.ywsoftware.oa.modules.sys.service.impl.AccountLogsService;
import java.util.HashMap;
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

	@Resource
	private AccountLogsService accountLogsService;
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

	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/loginLogs")
	@RequiresPermissions("sys:log:loginLogs")
	public PaginatedItems<AccountLogs> loginLogs(@RequestParam Map<String, Object> params) {
		PaginatedFilter filter = new PaginatedFilter();
		filter.setIndex(Integer.valueOf(params.get("index").toString()));
		filter.setSize(Integer.valueOf(params.get("size").toString()));
		filter.setFilters(new HashMap<String, String>(){{
			put("userId",params.get("userId")+"");
			put("cityName",params.get("cityName")+"");
		}});
		return accountLogsService.getLoginLogs(filter);
	}

}
