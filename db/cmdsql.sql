

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab_config
-- ----------------------------
DROP TABLE IF EXISTS `tab_config`;
CREATE TABLE `tab_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of tab_config
-- ----------------------------

-- ----------------------------
-- Table structure for tab_menu
-- ----------------------------
DROP TABLE IF EXISTS `tab_menu`;
CREATE TABLE `tab_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of tab_menu
-- ----------------------------
INSERT INTO `tab_menu` VALUES ('1', '0', '用户管理', null, null, '0', '设置', '0');
INSERT INTO `tab_menu` VALUES ('2', '1', '用户列表', 'sys/user', null, '1', '员工', '1');
INSERT INTO `tab_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', '审批', '2');
INSERT INTO `tab_menu` VALUES ('4', '31', '菜单列表', 'sys/menu', null, '1', '', '3');
INSERT INTO `tab_menu` VALUES ('15', '2', '查看', null, 'sys:account:manageAccounts', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `tab_menu` VALUES ('29', '31', '操作日志', 'sys/log', 'sys:log:list', '1', null, '7');
INSERT INTO `tab_menu` VALUES ('30', '31', '登录日志', 'sys/login-log', 'sys:log:loginLogs', '1', null, '7');
INSERT INTO `tab_menu` VALUES ('31', '0', '系统设置', '', null, '0', '设置', '0');
INSERT INTO `tab_menu` VALUES ('32', '31', '基础设置', 'tenant/edit', null, '1', null, '0');
INSERT INTO `tab_menu` VALUES ('33', '31', '管理员变更', 'sys/manage-Account', null, '1', null, '0');
INSERT INTO `tab_menu` VALUES ('34', '1', '员工列表', 'iframe/employeeList', null, '1', null, '3');
INSERT INTO `tab_menu` VALUES ('35', '1', '组织机构', 'iframe/organization', null, '1', null, '4');
INSERT INTO `tab_menu` VALUES ('36', '1', '用户权限', 'iframe/rolePermissions', null, '1', null, '5');
INSERT INTO `tab_menu` VALUES ('37', '1', '员工信息', 'iframe/employeeEdit', null, '1', null, '6');

-- ----------------------------
-- Table structure for tab_role
-- ----------------------------
DROP TABLE IF EXISTS `tab_role`;
CREATE TABLE `tab_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(255) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of tab_role
-- ----------------------------
INSERT INTO `tab_role` VALUES ('5', '普通角色', '普通角色', '15EC8080-5A50-4B8D-A94C-0A514EF1FBB1', '2018-11-22 09:24:17');

-- ----------------------------
-- Table structure for tab_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tab_role_menu`;
CREATE TABLE `tab_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of tab_role_menu
-- ----------------------------
INSERT INTO `tab_role_menu` VALUES ('101', '5', '15');
INSERT INTO `tab_role_menu` VALUES ('102', '5', '19');
INSERT INTO `tab_role_menu` VALUES ('103', '5', '23');
INSERT INTO `tab_role_menu` VALUES ('104', '5', '29');
INSERT INTO `tab_role_menu` VALUES ('105', '5', '30');
INSERT INTO `tab_role_menu` VALUES ('106', '5', '32');
INSERT INTO `tab_role_menu` VALUES ('107', '5', '33');
INSERT INTO `tab_role_menu` VALUES ('108', '5', '-666666');
INSERT INTO `tab_role_menu` VALUES ('109', '5', '1');
INSERT INTO `tab_role_menu` VALUES ('110', '5', '2');
INSERT INTO `tab_role_menu` VALUES ('111', '5', '3');
INSERT INTO `tab_role_menu` VALUES ('112', '5', '31');
INSERT INTO `tab_role_menu` VALUES ('113', '5', '4');

-- ----------------------------
-- Table structure for tab_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tab_user_role`;
CREATE TABLE `tab_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of tab_user_role
-- ----------------------------
INSERT INTO `tab_user_role` VALUES ('9', '15EC8080-5A60-4911-9712-CA5D2BC01F54', '5');
INSERT INTO `tab_user_role` VALUES ('10', '16223DC3-E410-6862-55B6-2A93088AD2F3', '5');

-- ----------------------------
-- Table structure for tab_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tab_user_token`;
CREATE TABLE `tab_user_token` (
  `user_id` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of tab_user_token
-- ----------------------------
INSERT INTO `tab_user_token` VALUES ('1', 'a6285a74e562cc4346f1d51d2c8919d2', '2018-11-21 02:31:28', '2018-11-20 14:31:28');
INSERT INTO `tab_user_token` VALUES ('15EC8080-5A50-4B8D-A94C-0A514EF1FBB1', '83b9adf35c6f0007751c4338f5d1cb8e', '2018-11-23 01:38:18', '2018-11-22 13:38:18');
INSERT INTO `tab_user_token` VALUES ('15EC8080-5A60-4911-9712-CA5D2BC01F54', 'c8be3d054742a3b9f1eeb9d9ad943514', '2018-11-22 21:36:05', '2018-11-22 09:36:05');
INSERT INTO `tab_user_token` VALUES ('16223DC3-E410-6862-55B6-2A93088AD2F3', '71c5f4d85ed8c4cf544bf901ae6c219c', '2018-11-22 22:37:17', '2018-11-22 10:37:17');
INSERT INTO `tab_user_token` VALUES ('1636E4CF-2BB0-4D75-BD74-0EC3E2E020EF', '31093e84a9b247556f2c4a2bbed714c6', '2018-11-22 06:21:12', '2018-11-21 18:21:12');
INSERT INTO `tab_user_token` VALUES ('2', '8e6c8a765510ddba7950ae15c6981a9a', '2018-11-01 05:31:30', '2018-10-31 17:31:30');


-- ----------------------------
-- Table structure for user_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `user_operation_log`;
CREATE TABLE `user_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of user_operation_log
-- ----------------------------
INSERT INTO `user_operation_log` VALUES ('1', 'admin', '保存角色', 'com.oservice.admin.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"访客\",\"remark\":\"访客\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,-666666,1],\"createTime\":\"Oct 31, 2018 5:30:22 PM\"}]', '618', '0:0:0:0:0:0:0:1', '2018-10-31 17:30:23');
INSERT INTO `user_operation_log` VALUES ('2', 'admin', '保存用户', 'com.oservice.admin.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"xiewl\",\"password\":\"e352431936451eb167b01ad91fb45c19f9b071cd6098ed19c6f31c9bdb3ebcb9\",\"salt\":\"kARkRvfBuCjPqNjCJpGu\",\"email\":\"455@qq.com\",\"mobile\":\"15087429692\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 31, 2018 5:31:07 PM\"}]', '569', '0:0:0:0:0:0:0:1', '2018-10-31 17:31:08');
INSERT INTO `user_operation_log` VALUES ('3', 'admin', '修改菜单', 'com.oservice.admin.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"Category\",\"orderNum\":1}]', '42', '0:0:0:0:0:0:0:1', '2018-11-01 09:27:59');
INSERT INTO `user_operation_log` VALUES ('4', 'admin', '修改菜单', 'com.oservice.admin.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"员工\",\"orderNum\":1}]', '15', '0:0:0:0:0:0:0:1', '2018-11-01 09:28:29');
INSERT INTO `user_operation_log` VALUES ('5', 'admin', '修改菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"用户管理\",\"type\":0,\"icon\":\"设置\",\"orderNum\":0}]', '26', '0:0:0:0:0:0:0:1', '2018-11-20 10:26:20');
INSERT INTO `user_operation_log` VALUES ('6', 'admin', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"系统设置\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"设置\",\"orderNum\":0}]', '28', '0:0:0:0:0:0:0:1', '2018-11-20 10:27:06');
INSERT INTO `user_operation_log` VALUES ('7', 'admin', '修改菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"系统设置\",\"type\":0,\"icon\":\"设置\",\"orderNum\":0}]', '24', '0:0:0:0:0:0:0:1', '2018-11-20 10:29:29');
INSERT INTO `user_operation_log` VALUES ('8', 'admin', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"基础设置\",\"url\":\"暂无\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0}]', '16', '0:0:0:0:0:0:0:1', '2018-11-20 10:31:28');
INSERT INTO `user_operation_log` VALUES ('9', 'admin', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":33,\"parentId\":31,\"name\":\"管理员变更\",\"url\":\"管理员变更\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0}]', '39', '0:0:0:0:0:0:0:1', '2018-11-20 10:31:58');
INSERT INTO `user_operation_log` VALUES ('10', 'admin', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":34,\"parentId\":31,\"name\":\"管理员变更\",\"url\":\"管理员变更\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0}]', '120', '0:0:0:0:0:0:0:1', '2018-11-20 10:32:00');
INSERT INTO `user_operation_log` VALUES ('11', '15087429695', '保存角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"阿大声道\",\"remark\":\"大\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,5,-666666],\"createTime\":\"Nov 20, 2018 5:58:24 PM\"}]', '133', '0:0:0:0:0:0:0:1', '2018-11-20 17:58:35');
INSERT INTO `user_operation_log` VALUES ('12', '15087429695', '删除角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.delete()', '[[4]]', '38', '0:0:0:0:0:0:0:1', '2018-11-20 17:58:47');
INSERT INTO `user_operation_log` VALUES ('13', '15087429695', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"测试菜单\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"数据\",\"orderNum\":0}]', '40', '0:0:0:0:0:0:0:1', '2018-11-20 17:59:53');
INSERT INTO `user_operation_log` VALUES ('14', '15087429695', '修改菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":35,\"parentId\":0,\"name\":\"测试菜单\",\"url\":\"大声道\",\"perms\":\"打算\",\"type\":1,\"icon\":\"考勤\",\"orderNum\":0}]', '27', '0:0:0:0:0:0:0:1', '2018-11-20 18:00:10');
INSERT INTO `user_operation_log` VALUES ('15', '15087429695', '删除菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.delete()', '[35]', '38', '0:0:0:0:0:0:0:1', '2018-11-20 18:00:30');
INSERT INTO `user_operation_log` VALUES ('16', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"谢文林\",\"phone\":\"15087429695\",\"email\":\"xiewl@ywsoftware.com\",\"isManager\":false,\"roleIdList\":[2],\"id\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\"}]', '196067', '0:0:0:0:0:0:0:1', '2018-11-21 17:47:18');
INSERT INTO `user_operation_log` VALUES ('17', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"谢文林\",\"phone\":\"15087429695\",\"email\":\"xiewl@ywsoftware.com\",\"isManager\":false,\"roleIdList\":[2,1],\"id\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\"}]', '22825', '0:0:0:0:0:0:0:1', '2018-11-21 17:47:41');
INSERT INTO `user_operation_log` VALUES ('18', '15087429695', '删除角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.delete()', '[[1]]', '50', '0:0:0:0:0:0:0:1', '2018-11-21 18:00:01');
INSERT INTO `user_operation_log` VALUES ('19', '15087429695', '修改角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":2,\"roleName\":\"普通角色\",\"remark\":\"普通角色\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[29,30,32,33,-666666,31]}]', '91', '0:0:0:0:0:0:0:1', '2018-11-21 18:00:29');
INSERT INTO `user_operation_log` VALUES ('20', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"系统管理员\",\"phone\":\"13810599731\",\"isManager\":false,\"roleIdList\":[2],\"id\":\"15EC8080-55B0-4C53-BBED-16350F6684YY\"}]', '52', '0:0:0:0:0:0:0:1', '2018-11-21 18:00:56');
INSERT INTO `user_operation_log` VALUES ('21', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"系统管理员\",\"phone\":\"13810599731\",\"isManager\":false,\"roleIdList\":[2],\"id\":\"15EC8080-55B0-4C53-BBED-16350F6684YY\"}]', '10326', '0:0:0:0:0:0:0:1', '2018-11-21 18:05:04');
INSERT INTO `user_operation_log` VALUES ('22', '15087429695', '修改角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":2,\"roleName\":\"普通角色\",\"remark\":\"普通角色\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[31,4,23,24,25,26,29,30,32,33,-666666]}]', '98', '0:0:0:0:0:0:0:1', '2018-11-21 18:05:46');
INSERT INTO `user_operation_log` VALUES ('23', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"系统管理员\",\"phone\":\"13810599731\",\"isManager\":false,\"roleIdList\":[2],\"id\":\"15EC8080-55B0-4C53-BBED-16350F6684YY\"}]', '18340', '0:0:0:0:0:0:0:1', '2018-11-21 18:06:34');
INSERT INTO `user_operation_log` VALUES ('24', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"ys\",\"phone\":\"13810599731\",\"email\":\"wqd@wqd.com\",\"isManager\":false,\"roleIdList\":[2],\"id\":\"1636E4CF-2BB0-4D75-BD74-0EC3E2E020EF\"}]', '111', '0:0:0:0:0:0:0:1', '2018-11-21 18:21:00');
INSERT INTO `user_operation_log` VALUES ('25', '15087429695', '删除菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.delete()', '[16]', '0', '0:0:0:0:0:0:0:1', '2018-11-22 09:13:14');
INSERT INTO `user_operation_log` VALUES ('26', '15087429695', '删除菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.delete()', '[16]', '0', '0:0:0:0:0:0:0:1', '2018-11-22 09:13:20');
INSERT INTO `user_operation_log` VALUES ('27', '15087429695', '删除角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.delete()', '[[2]]', '42', '0:0:0:0:0:0:0:1', '2018-11-22 09:20:45');
INSERT INTO `user_operation_log` VALUES ('28', '15087429695', '保存角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"普通角色\",\"remark\":\"普通角色\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[32,33,-666666,31],\"createTime\":\"Nov 22, 2018 9:24:17 AM\"}]', '10241', '0:0:0:0:0:0:0:1', '2018-11-22 09:24:20');
INSERT INTO `user_operation_log` VALUES ('29', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"章立强\",\"phone\":\"zhanglq\",\"email\":\"zhanglq@ywsoftware.com\",\"isManager\":false,\"roleIdList\":[5],\"id\":\"15EC8080-5A60-4911-9712-CA5D2BC01F54\"}]', '53', '0:0:0:0:0:0:0:1', '2018-11-22 09:25:13');
INSERT INTO `user_operation_log` VALUES ('30', '15087429695', '修改用户', 'com.ywsoftware.oa.modules.sys.controller.AccountController.update()', '[{\"name\":\"王琼\",\"phone\":\"15158058018\",\"email\":\"wangq@ywsoftware.com\",\"isManager\":false,\"roleIdList\":[5],\"id\":\"16223DC3-E410-6862-55B6-2A93088AD2F3\"}]', '82', '0:0:0:0:0:0:0:1', '2018-11-22 10:27:37');
INSERT INTO `user_operation_log` VALUES ('31', '15087429695', '修改角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":5,\"roleName\":\"普通角色\",\"remark\":\"普通角色\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[23,29,30,32,33,-666666,31,4]}]', '64', '0:0:0:0:0:0:0:1', '2018-11-22 10:29:37');
INSERT INTO `user_operation_log` VALUES ('32', '15087429695', '修改角色', 'com.ywsoftware.oa.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":5,\"roleName\":\"普通角色\",\"remark\":\"普通角色\",\"createUserId\":\"15EC8080-5A50-4B8D-A94C-0A514EF1FBB1\",\"menuIdList\":[15,19,23,29,30,32,33,-666666,1,2,3,31,4]}]', '119', '0:0:0:0:0:0:0:1', '2018-11-22 10:35:36');
INSERT INTO `user_operation_log` VALUES ('33', '15087429695', '保存菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"员工信息\",\"url\":\"iframe/employeeEdit\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0}]', '20', '0:0:0:0:0:0:0:1', '2018-11-22 14:10:04');
INSERT INTO `user_operation_log` VALUES ('34', '15087429695', '删除菜单', 'com.ywsoftware.oa.modules.sys.controller.SysMenuController.delete()', '[36]', '24', '0:0:0:0:0:0:0:1', '2018-11-22 14:11:00');

