/******************************************/
/*   数据库全名 = hasakei   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE `config_info`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `config_name`  varchar(255) NOT NULL COMMENT 'data_id',
    `environment`  varchar(255) NOT NULL COMMENT '配置文件所属环境',
    `content`      longtext     NOT NULL COMMENT 'content',
    `md5`          varchar(32)  DEFAULT NULL COMMENT 'md5',
    `gmt_create`   BIGINT(15)   NOT NULL COMMENT '创建时间',
    `gmt_modified` BIGINT(15)   NOT NULL COMMENT '修改时间',
    `config_desc`  varchar(256) DEFAULT NULL COMMENT '配置描述',
    `user_id`      integer      NOT NULL,
    `username`     varchar(255) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_config_nameenvironment` (`config_name`, `environment`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='config_info';

CREATE TABLE `history_config_info`
(
    `id`             bigint(64) unsigned NOT NULL AUTO_INCREMENT,
    `config_info_id` bigint(20) unsigned NOT NULL,
    `config_name`    varchar(255)        NOT NULL,
    `environment`    varchar(128)        NOT NULL,
    `content`        longtext            NOT NULL,
    `md5`            varchar(32)  DEFAULT NULL,
    `gmt_create`     bigint(15)          NOT NULL,
    `gmt_modified`   bigint(15)          NOT NULL,
    `src_user`       text,
    `src_ip`         varchar(20)  DEFAULT NULL,
    `op_type`        char(10)     DEFAULT NULL,
    `tenant_id`      varchar(128) DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    KEY `idx_gmt_create` (`gmt_create`),
    KEY `idx_gmt_modified` (`gmt_modified`),
    KEY `idx_config_name` (`config_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='配置历史记录表';

CREATE TABLE users
(
    username varchar(50)  NOT NULL PRIMARY KEY,
    password varchar(500) NOT NULL,
    enabled  boolean      NOT NULL
);

CREATE TABLE roles
(
    username varchar(50) NOT NULL,
    role     varchar(50) NOT NULL,
    constraint uk_username_role UNIQUE (username, role)
);

CREATE TABLE permissions
(
    role     varchar(50)  NOT NULL,
    resource varchar(512) NOT NULL,
    action   varchar(8)   NOT NULL,
    constraint uk_role_permission UNIQUE (role, resource, action)
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');