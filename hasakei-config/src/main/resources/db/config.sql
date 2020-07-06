/******************************************/
/*   数据库全名 = hasakei   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE `config_info`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`         varchar(255) NOT NULL COMMENT 'data_id',
    `environment`  varchar(255) NOT NULL COMMENT '配置文件所属环境',
    `content`      longtext     NOT NULL COMMENT 'content',
    `md5`          varchar(32)  DEFAULT NULL COMMENT 'md5',
    `gmt_create`   BIGINT(15)   NOT NULL COMMENT '创建时间',
    `gmt_modified` BIGINT(15)   NOT NULL COMMENT '修改时间',
    `config_desc`  varchar(256) DEFAULT NULL COMMENT '配置描述',
    `type`         varchar(64)  NOT NULL,
    `user_id`      integer      NOT NULL,
    `user_name`    varchar(255) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_nameenvironment` (`name`, `environment`)
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
    `md5`            varchar(32)         DEFAULT NULL,
    `gmt_create`     bigint(15)    NOT NULL,
    `gmt_modified`   bigint(15)    NOT NULL,
    `src_user`       text,
    `src_ip`         varchar(20)                  DEFAULT NULL,
    `op_type`        char(10)                     DEFAULT NULL,
    `tenant_id`      varchar(128)                 DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    KEY `idx_gmt_create` (`gmt_create`),
    KEY `idx_gmt_modified` (`gmt_modified`),
    KEY `idx_config_name` (`config_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='配置历史记录表';

