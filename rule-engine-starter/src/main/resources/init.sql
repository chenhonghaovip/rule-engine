create table rule_scene
(
    id          bigint       not null primary key auto_increment comment '主键',
    scene_code  varchar(50)  not null comment '场景编码',
    scene_name  varchar(50)  not null comment '场景名称',
    scene_desc  varchar(200) not null comment '场景描述',
    group_code  varchar(50)  not null comment '分组编码',
    yn          tinyint(1)   not null default 1 comment '是否删除 1-否，0-是',
    create_time datetime     not null default current_timestamp comment '创建时间',
    modify_time datetime comment '更新时间',
    creator     varchar(50)  not null comment '创建人',
    modifier    varchar(50) comment '修改人',
    tenant      varchar(50)  not null comment '租户'
) engine = innodb
  default charset = utf8mb4 comment '规则场景表';

create table rule_scene_action
(
    id          bigint      not null primary key auto_increment comment '主键',
    scene_code  varchar(50) not null comment '场景编码',
    action_code varchar(50) not null comment '行为编码',
    action_type varchar(50) not null comment '行为类型',
    action      longtext comment '行为内容',
    yn          tinyint(1)  not null default 1 comment '是否删除 1-否，0-是',
    create_time datetime    not null default current_timestamp comment '创建时间',
    modify_time datetime comment '更新时间',
    creator     varchar(50) not null comment '创建人',
    modifier    varchar(50) comment '修改人',
    tenant      varchar(50) not null comment '租户'
) engine = innodb
  default charset = utf8mb4 comment '规则场景行为表';

create table rule_factor_group
(
    id          bigint      not null primary key auto_increment comment '主键',
    group_code  varchar(50) not null comment '分组编码',
    group_name  varchar(50) not null comment '分组名称',
    yn          tinyint(1)  not null default 1 comment '是否删除 1-否，0-是',
    create_time datetime    not null default current_timestamp comment '创建时间',
    modify_time datetime comment '更新时间',
    creator     varchar(50) not null comment '创建人',
    modifier    varchar(50) comment '修改人',
    tenant      varchar(50) not null comment '租户'
) engine = innodb
  default charset = utf8mb4 comment '规则因子分组表';

create table rule_factor
(
    id                  bigint      not null primary key auto_increment comment '主键',
    factor_code         varchar(50) not null comment '因子编码',
    factor_full_code    varchar(50) not null comment '因子全编码',
    factor_name         varchar(50) not null comment '因子名称',
    group_code          varchar(50) not null comment '分组编码',
    factor_type         varchar(10) not null comment '因子类型（日期、数值、集合、布尔、文本）',
    constant_type       varchar(10) not null comment '常量类型（Input:输入，Enum:枚举，Script:脚本）',
    constant_value      varchar(50) comment '常量值',
    factor_script_param varchar(100) comment '脚本参数',
    factor_script       longtext comment '脚本代码',
    remark              varchar(200) comment '备注',
    status              tinyint(1)  not null comment '状态（0:否，1:是）',
    ext_info            longtext comment '扩展信息',
    yn                  tinyint(1)  not null default 1 comment '是否删除 1-否，0-是',
    create_time         datetime    not null default current_timestamp comment '创建时间',
    modify_time         datetime comment '更新时间',
    creator             varchar(50) not null comment '创建人',
    modifier            varchar(50) comment '修改人',
    tenant              varchar(50) not null comment '租户'
) engine = innodb
  default charset = utf8mb4 comment '规则因子表';

create table rule_pack
(
    id                    bigint auto_increment comment '主键' primary key,
    rule_pack_code        varchar(50)                          not null comment '规则包编码',
    rule_pack_name        varchar(50)                          not null comment '规则包名称',
    rule_pack_type        varchar(20)                          not null comment '规则包类型',
    rule_arrange_strategy varchar(200)                         null comment '规则调度策略',
    rule_content          longtext                             not null comment '规则信息',
    pack_params           longtext                             null comment '规则包入参',
    version               int        default 1                 not null comment '版本号',
    latest                int        default 1                 not null comment '是否最新版本（0:否，1:是）',
    remark                varchar(200)                         null comment '备注',
    status                tinyint(1)                           not null default 1 comment '状态（0:否，1:是）',
    yn                    tinyint(1) default 1                 not null comment '是否删除 1-否，0-是',
    create_time           datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_time           datetime                             null comment '更新时间',
    creator               varchar(50)                          not null comment '创建人',
    modifier              varchar(50)                          null comment '修改人',
    tenant                varchar(50)                          not null comment '租户'
) engine = innodb
  default charset = utf8mb4 comment '规则包表';