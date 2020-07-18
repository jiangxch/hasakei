CREATE SCHEMA hasakei AUTHORIZATION hasakei;

/******************************************/
/*   数据库全名 = hasakei   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE config_info
(
    id           bigint(20) NOT NULL AUTO_INCREMENT ,
    config_name  varchar(255) NOT NULL,
    environment  varchar(255) NOT NULL,
    content      CLOB         NOT NULL,
    md5          varchar(32)           DEFAULT NULL,
    gmt_create   timestamp    NOT NULL DEFAULT '2010-05-05 00:00:00',
    gmt_modified timestamp    NOT NULL DEFAULT '2010-05-05 00:00:00',
    config_desc  varchar(256)          DEFAULT NULL,
    user_id      integer      NOT NULL,
    username     varchar(255) NOT NULL,

    constraint configinfo_id_key PRIMARY KEY (id),
    constraint uk_configinfo_confignameenvironment UNIQUE (config_name, environment)
);

CREATE TABLE history_config_info
(
    id          bigint(64) unsigned NOT NULL AUTO_INCREMENT,
    config_name varchar(255) NOT NULL,
    environment varchar(255) NOT NULL,
    config_info CLOB         NOT NULL,
    version     int          NOT NULL,
    gmt_create  timestamp    NOT NULL DEFAULT '2010-05-05 00:00:00',
    username    varchar(255) NOT NULL,

    constraint history_config_info_key PRIMARY KEY (id),
    constraint uk_history_config_info_confignameversion UNIQUE (config_name, version)
);

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

INSERT INTO users (username, password, enabled)
VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role)
VALUES ('nacos', 'ROLE_ADMIN');