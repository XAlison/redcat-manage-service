package com.ywsoftware.oa.common.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpStatus;

/**
 * 返回数据
 *
 * @author xiewl
 * @version 1.0
 */
public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public Result() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static Result error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static Result error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static Result error(int code, String msg) {
		Result result = new Result();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

	public static Result ok(String msg) {
		Result result = new Result();
		result.put("msg", msg);
		return result;
	}
	
	public static Result ok(Map<String, Object> map) {
		Result result = new Result();
		result.putAll(map);
		return result;
	}
	
	public static Result ok() {
		return new Result();
	}

	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
