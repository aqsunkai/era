-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述/名称',
   parent_id int(11) DEFAULT NULL COMMENT '父节点权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('10', '/member/changeSessionStatus.shtml', '用户Session踢出',null);
INSERT INTO `sys_permission` VALUES ('11', '/member/forbidUserById.shtml', '用户激活&禁止',null);
INSERT INTO `sys_permission` VALUES ('12', '/member/deleteUserById.shtml', '用户删除',null);
INSERT INTO `sys_permission` VALUES ('13', '/permission/addPermission2Role.shtml', '权限分配',null);
INSERT INTO `sys_permission` VALUES ('14', '/role/clearRoleByUserIds.shtml', '用户角色分配清空',null);
INSERT INTO `sys_permission` VALUES ('15', '/role/addRole2User.shtml', '角色分配保存',null);
INSERT INTO `sys_permission` VALUES ('16', '/role/deleteRoleById.shtml', '角色列表删除',null);
INSERT INTO `sys_permission` VALUES ('17', '/role/addRole.shtml', '角色列表添加',null);
INSERT INTO `sys_permission` VALUES ('18', '/role/index.shtml', '角色列表',null);
INSERT INTO `sys_permission` VALUES ('19', '/permission/allocation.shtml', '权限分配',null);
INSERT INTO `sys_permission` VALUES ('20', '/role/allocation.shtml', '角色分配',null);
INSERT INTO `sys_permission` VALUES ('4', '/permission/index.shtml', '权限列表',null);
INSERT INTO `sys_permission` VALUES ('6', '/permission/addPermission.shtml', '权限添加',null);
INSERT INTO `sys_permission` VALUES ('7', '/permission/deletePermissionById.shtml', '权限删除',null);
INSERT INTO `sys_permission` VALUES ('8', '/member/list.shtml', '用户列表',null);
INSERT INTO `sys_permission` VALUES ('9', '/member/online.shtml', '在线用户',null);

-- ----------------------------
-- Table structure for sys_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_init`;
CREATE TABLE `sys_permission_init` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `permission_init` varchar(255) DEFAULT NULL COMMENT '需要具备的权限',
  `sort` int(50) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon,kickout', '1');
INSERT INTO `sys_permission_init` VALUES ('2', '/ajaxLogin', 'anon,kickout', '2');
INSERT INTO `sys_permission_init` VALUES ('3', '/logout', 'logout,kickout', '3');
INSERT INTO `sys_permission_init` VALUES ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4');
INSERT INTO `sys_permission_init` VALUES ('5', '/**', 'user,kickout', '5');
INSERT INTO `sys_permission_init` VALUES ('6', '/getGifCode', 'anon,kickout', '2');
INSERT INTO `sys_permission_init` VALUES ('7', '/kickout', 'anon', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '100004');
INSERT INTO `sys_role` VALUES ('3', '权限角色', '100001');
INSERT INTO `sys_role` VALUES ('4', '用户中心', '100002');
INSERT INTO `sys_role` VALUES ('0', '角色管理', '100003');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rid` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `pid` varchar(64) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '4', '8');
INSERT INTO `sys_role_permission` VALUES ('10', '3', '14');
INSERT INTO `sys_role_permission` VALUES ('11', '3', '15');
INSERT INTO `sys_role_permission` VALUES ('12', '3', '16');
INSERT INTO `sys_role_permission` VALUES ('13', '3', '17');
INSERT INTO `sys_role_permission` VALUES ('14', '3', '18');
INSERT INTO `sys_role_permission` VALUES ('15', '3', '19');
INSERT INTO `sys_role_permission` VALUES ('16', '3', '20');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '4', '9');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('24', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('25', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('26', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('27', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('28', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('29', '1', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '4', '10');
INSERT INTO `sys_role_permission` VALUES ('30', '1', '18');
INSERT INTO `sys_role_permission` VALUES ('31', '1', '19');
INSERT INTO `sys_role_permission` VALUES ('32', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('4', '4', '11');
INSERT INTO `sys_role_permission` VALUES ('5', '4', '12');
INSERT INTO `sys_role_permission` VALUES ('6', '3', '4');
INSERT INTO `sys_role_permission` VALUES ('7', '3', '6');
INSERT INTO `sys_role_permission` VALUES ('8', '3', '7');
INSERT INTO `sys_role_permission` VALUES ('9', '3', '13');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` TINYINT DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin@qq.com', 'l9a0lajfwQ3VRSh4jUUJSQ==', NOW(), NOW(), '1');
INSERT INTO `sys_user` VALUES ('11', 'root', '8446666@qq.com', 'CBbObwI8VQ2MrKFGmnWL8w==', '2016-05-26 20:50:54', '2017-02-13 15:49:04', '1');
INSERT INTO `sys_user` VALUES ('12', '8446666', '8446666', 'x16YjoF1LNE=', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '1');
INSERT INTO `sys_user` VALUES ('13', '123', '123', 'KL/QrouuaRWRWlkI0mpwbw==', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '0');
INSERT INTO `sys_user` VALUES ('14', 'haiqin', '123123@qq.com', 'zrt4HMxuDq3V8tXGdIBKjA==', '2016-05-27 22:34:19', '2017-03-23 21:39:44', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `rid` varchar(64) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '12', '4');
INSERT INTO `sys_user_role` VALUES ('2', '11', '3');
INSERT INTO `sys_user_role` VALUES ('3', '11', '4');
INSERT INTO `sys_user_role` VALUES ('4', '1', '1');
