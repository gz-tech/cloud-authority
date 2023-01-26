-- ----------------------------
-- Table structure for biz_permission
-- ----------------------------
DROP TABLE IF EXISTS `biz_permission`;
CREATE TABLE `biz_permission` (
                                  `id` bigint NOT NULL,
                                  `res_id` bigint NOT NULL COMMENT '外部资源ID',
                                  `action` int NOT NULL DEFAULT 0 COMMENT '授权类型 0-query',
                                  `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='授权关系表（外部）';

-- ----------------------------
-- Table structure for biz_resource
-- ----------------------------
DROP TABLE IF EXISTS `biz_resource`;
CREATE TABLE `biz_resource` (
                                `id` bigint NOT NULL,
                                `system_id` bigint NOT NULL COMMENT '系统ID',
                                `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名',
                                `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '资源描述',
                                `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '路径地址',
                                `res_type` int NOT NULL COMMENT '类型 0-菜单 1-接口',
                                `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表(业务)';

-- ----------------------------
-- Table structure for biz_role
-- ----------------------------
DROP TABLE IF EXISTS `biz_role`;
CREATE TABLE `biz_role` (
                            `id` bigint NOT NULL COMMENT '主键',
                            `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色值',
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色描述',
                            `system_id` bigint NOT NULL COMMENT '系统ID',
                            `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                            PRIMARY KEY (`id`),
                            KEY `idx_id_system_id` (`id`,`system_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表（业务）';

-- ----------------------------
-- Table structure for biz_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `biz_role_permission`;
CREATE TABLE `biz_role_permission` (
                                       `id` bigint NOT NULL,
                                       `role_id` bigint NOT NULL COMMENT '外部角色ID',
                                       `permission_id` bigint NOT NULL COMMENT '外部授权关系ID',
                                       `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色授权关系表（外部）';

-- ----------------------------
-- Table structure for biz_role_user
-- ----------------------------
DROP TABLE IF EXISTS `biz_role_user`;
CREATE TABLE `biz_role_user` (
                                 `id` bigint NOT NULL COMMENT '主键',
                                 `role_id` bigint NOT NULL COMMENT '外部角色ID',
                                 `user_id` bigint NOT NULL COMMENT '外部用户ID',
                                 `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表（业务）';

-- ----------------------------
-- Table structure for biz_user
-- ----------------------------
DROP TABLE IF EXISTS `biz_user`;
CREATE TABLE `biz_user` (
                            `id` bigint NOT NULL,
                            `uid` varchar(255) NOT NULL DEFAULT '' COMMENT '外部系统用户ID',
                            `system_id` bigint NOT NULL COMMENT '内部系统ID',
                            `name` varchar(255) NOT NULL COMMENT '用户名',
                            `mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '手机号',
                            `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
                            `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表（外部）';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
                                  `id` bigint NOT NULL,
                                  `code` varchar(255) NOT NULL COMMENT '权限code(唯一）',
                                  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '接口权限描述',
                                  `res_type` int NOT NULL COMMENT '权限类型 0-接口url 1-前端路由 2-菜单',
                                  `path` varchar(255) NOT NULL COMMENT '路径地址',
                                  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
                                  `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色接口权限表（内部）';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
                                       `id` bigint NOT NULL,
                                       `role_type` bigint NOT NULL COMMENT '内部角色类型 0-超管 1-系统owner 2-开发',
                                       `permission_id` bigint NOT NULL,
                                       `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色接口权限表（内部）';

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
                                 `id` bigint NOT NULL,
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `role_type` int NOT NULL COMMENT '系统角色类型 0-超管 1-系统owner 2-开发',
                                 `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户系统角色关联表（内部）';

-- ----------------------------
-- Table structure for sys_system
-- ----------------------------
DROP TABLE IF EXISTS `sys_system`;
CREATE TABLE `sys_system` (
                              `id` bigint NOT NULL,
                              `name` varchar(255) NOT NULL COMMENT '系统名称',
                              `code` varchar(255) NOT NULL COMMENT '系统唯一值',
                              `description` varchar(255) NOT NULL DEFAULT '' COMMENT '系统描述',
                              `owner_user_id` bigint NOT NULL DEFAULT 0 COMMENT '系统owner(自动授予其系统管理员权限)',
                              `secret` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '系统密钥',
                              `owner_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'Owner name',
                              `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                              `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                              `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统表（内部）';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL COMMENT '主键',
                            `uid` varchar(255) NOT NULL COMMENT '用户登录唯一ID',
                            `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
                            `mobile` varchar(32) NOT NULL DEFAULT '',
                            `email` varchar(255) NOT NULL DEFAULT '',
                            `blocked` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 nonblocked 1 blocked',
                            `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表（内部）';

-- ----------------------------
-- Table structure for sys_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_resource`;
CREATE TABLE `sys_user_resource` (
                                     `id` bigint NOT NULL,
                                     `user_id` bigint NOT NULL COMMENT '用户ID',
                                     `relation_type` int NOT NULL DEFAULT '1' COMMENT '关联类型 1-系统owner 2-系统developer',
                                     `resource_id` bigint NOT NULL COMMENT '资源ID relation_type为1-系统ID',
                                     `del_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0 valid 1 deleted',
                                     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                                     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户资源表（内部）';


alter table biz_permission modify COLUMN del_flag BIGINT;
alter table biz_resource modify COLUMN del_flag BIGINT;
alter table biz_role modify COLUMN del_flag BIGINT;
alter table biz_role_permission modify COLUMN del_flag BIGINT;
alter table biz_role_user modify COLUMN del_flag BIGINT;
alter table biz_user modify COLUMN del_flag BIGINT;
alter table sys_permission modify COLUMN del_flag BIGINT;
alter table sys_role_permission modify COLUMN del_flag BIGINT;
alter table sys_role_user modify COLUMN del_flag BIGINT;
alter table sys_system modify COLUMN del_flag BIGINT;
alter table sys_user modify COLUMN del_flag BIGINT;
alter table sys_user_resource modify COLUMN del_flag BIGINT;

alter table biz_permission alter column del_flag set default 0;
alter table biz_resource alter column del_flag set default 0;
alter table biz_role alter column del_flag set default 0;
alter table biz_role_permission alter column del_flag set default 0;
alter table biz_role_user alter column del_flag set default 0;
alter table biz_user alter column del_flag set default 0;
alter table sys_permission alter column del_flag set default 0;
alter table sys_role_permission alter column del_flag set default 0;
alter table sys_role_user alter column del_flag set default 0;
alter table sys_system alter column del_flag set default 0;
alter table sys_user alter column del_flag set default 0;
alter table sys_user_resource alter column del_flag set default 0;