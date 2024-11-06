package com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RuleSceneActionDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    public static final RuleSceneAction ruleSceneAction = new RuleSceneAction();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.id")
    public static final SqlColumn<Long> id = ruleSceneAction.id;

    /**
     * Database Column Remarks:
     * 场景编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.scene_code")
    public static final SqlColumn<String> sceneCode = ruleSceneAction.sceneCode;

    /**
     * Database Column Remarks:
     * 行为编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.action_code")
    public static final SqlColumn<String> actionCode = ruleSceneAction.actionCode;

    /**
     * Database Column Remarks:
     * 行为类型
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.action_type")
    public static final SqlColumn<String> actionType = ruleSceneAction.actionType;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.yn")
    public static final SqlColumn<Boolean> yn = ruleSceneAction.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.create_time")
    public static final SqlColumn<Date> createTime = ruleSceneAction.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.modify_time")
    public static final SqlColumn<Date> modifyTime = ruleSceneAction.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.creator")
    public static final SqlColumn<String> creator = ruleSceneAction.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.modifier")
    public static final SqlColumn<String> modifier = ruleSceneAction.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.tenant")
    public static final SqlColumn<String> tenant = ruleSceneAction.tenant;

    /**
     * Database Column Remarks:
     * 行为内容
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene_action.action")
    public static final SqlColumn<String> action = ruleSceneAction.action;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    public static final class RuleSceneAction extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> sceneCode = column("scene_code", JDBCType.VARCHAR);

        public final SqlColumn<String> actionCode = column("action_code", JDBCType.VARCHAR);

        public final SqlColumn<String> actionType = column("action_type", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public final SqlColumn<String> action = column("`action`", JDBCType.LONGVARCHAR);

        public RuleSceneAction() {
            super("rule_scene_action");
        }
    }
}