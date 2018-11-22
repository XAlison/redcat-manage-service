package com.ywsoftware.oa.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 *
 * @author xiewl
 * @version 1.0
 */
public class Constant {
	/** 超级管理员ID */
	public static final List<String> SUPER_ADMIN=new ArrayList<String>(){{
	    add("15EC8080-5A50-4B8D-A94C-0A514EF1FBB1");
        add("15EC8080-5A60-4911-9712-CA5D2BC01F54");
        add("15EC8080-5DF0-4F00-9892-E324C83F5CA0");
    }};

	/**
	 * 菜单类型
	 *
     * @author xiewl
     * @version 1.0
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
