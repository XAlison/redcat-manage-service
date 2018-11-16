/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : os_admin

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-11-16 14:21:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('01320ab9-a8cf-4024-83b5-3ea8ba651f32', 'xx7n6', '2018-11-16 14:21:10');
INSERT INTO `sys_captcha` VALUES ('12e17dfb-93f9-49b3-8541-7d5473f734b0', 'afnxa', '2018-11-16 13:12:40');
INSERT INTO `sys_captcha` VALUES ('1ce6b139-d7a1-42ff-8123-c1ebc909cdff', '34mdy', '2018-10-31 18:15:06');
INSERT INTO `sys_captcha` VALUES ('2271acc6-222e-41e6-89c7-fc185b444757', 'wm7ga', '2018-10-31 18:15:30');
INSERT INTO `sys_captcha` VALUES ('3d07c555-a189-4e91-8c45-60c1874b13e6', '422nc', '2018-11-16 13:05:44');
INSERT INTO `sys_captcha` VALUES ('44a6453d-db4a-44f1-8332-9f59ad87e575', 'f7ca7', '2018-11-16 14:21:13');
INSERT INTO `sys_captcha` VALUES ('4ab39dd3-efbe-4684-899c-937090cbcb61', '34dgn', '2018-11-16 14:21:14');
INSERT INTO `sys_captcha` VALUES ('551bd25c-c120-4175-8a32-9013d3ed5f67', 'nx6gf', '2018-11-16 13:01:01');
INSERT INTO `sys_captcha` VALUES ('55645c89-b478-4b50-8bba-a19ac925d286', 'x7pm8', '2018-10-31 17:31:19');
INSERT INTO `sys_captcha` VALUES ('61dfeef1-804e-4405-8473-e2cd16a40af2', '24dxf', '2018-11-16 13:05:21');
INSERT INTO `sys_captcha` VALUES ('807c8966-1138-4979-8dc0-c4ecf30ddd98', '3yfbp', '2018-11-16 14:21:12');
INSERT INTO `sys_captcha` VALUES ('9e8ed163-5b48-4e30-8b39-3ee69144e9db', '2fpfa', '2018-11-16 13:13:20');
INSERT INTO `sys_captcha` VALUES ('d17714df-4315-466a-8878-f38e5407176e', 'f8pby', '2018-10-31 17:36:14');
INSERT INTO `sys_captcha` VALUES ('e903827d-2a6f-4eb2-8372-0303ac2d56b2', 'xgc4p', '2018-11-16 13:12:42');
INSERT INTO `sys_captcha` VALUES ('f3fac1eb-0a75-4490-8819-3b441dd4920c', '3mwpe', '2018-11-16 13:07:02');
INSERT INTO `sys_captcha` VALUES ('f80a7720-4b35-40f5-85a3-f8a648d14701', 'b6m3n', '2018-10-31 17:40:27');
INSERT INTO `sys_captcha` VALUES ('fa9b7657-208b-48f7-8de7-e5bc697d47b3', 'nw472', '2018-10-31 17:40:29');
INSERT INTO `sys_captcha` VALUES ('fb74644c-94e1-4afb-8175-fb4a19638929', 'gb3aw', '2018-10-31 17:36:22');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存角色', 'com.oservice.admin.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"访客\",\"remark\":\"访客\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,-666666,1],\"createTime\":\"Oct 31, 2018 5:30:22 PM\"}]', '618', '0:0:0:0:0:0:0:1', '2018-10-31 17:30:23');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存用户', 'com.oservice.admin.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"xiewl\",\"password\":\"e352431936451eb167b01ad91fb45c19f9b071cd6098ed19c6f31c9bdb3ebcb9\",\"salt\":\"kARkRvfBuCjPqNjCJpGu\",\"email\":\"455@qq.com\",\"mobile\":\"15087429692\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 31, 2018 5:31:07 PM\"}]', '569', '0:0:0:0:0:0:0:1', '2018-10-31 17:31:08');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改菜单', 'com.oservice.admin.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"Category\",\"orderNum\":1}]', '42', '0:0:0:0:0:0:0:1', '2018-11-01 09:27:59');
INSERT INTO `sys_log` VALUES ('4', 'admin', '修改菜单', 'com.oservice.admin.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"员工\",\"orderNum\":1}]', '15', '0:0:0:0:0:0:0:1', '2018-11-01 09:28:29');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', '设置', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户列表', 'sys/user', null, '1', '员工', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', '审批', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单列表', 'sys/menu', null, '1', '部门', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:9001/redcat-manage/druid/sql.html', null, '1', '运营', '4');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', '轨迹', '7');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '访客', '访客', '1', '2018-10-31 17:30:23');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '-666666');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('2', 'xiewl', 'e352431936451eb167b01ad91fb45c19f9b071cd6098ed19c6f31c9bdb3ebcb9', 'kARkRvfBuCjPqNjCJpGu', '455@qq.com', '15087429692', '1', '1', '2018-10-31 17:31:07');
INSERT INTO `sys_user` VALUES ('3', 'admin2', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('4', 'admin3', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('5', 'admin6', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('7', 'admin7', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('8', 'admin8', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('9', 'admin9', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('10', 'admin10', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('11', 'admin11', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');
INSERT INTO `sys_user` VALUES ('12', 'admin12', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '821300801@qq.com', '15622157853', '1', '1', '2018-10-10 10:10:10');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '97bfbe26247ba0b86f7f031409e007aa', '2018-11-17 02:16:51', '2018-11-16 14:16:51');
INSERT INTO `sys_user_token` VALUES ('2', '8e6c8a765510ddba7950ae15c6981a9a', '2018-11-01 05:31:30', '2018-10-31 17:31:30');
