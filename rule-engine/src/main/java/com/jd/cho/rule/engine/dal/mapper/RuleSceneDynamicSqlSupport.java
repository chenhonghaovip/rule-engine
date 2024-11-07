package com.jd.cho.rule.engine.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RuleSceneDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public static final RuleScene ruleScene = new RuleScene();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.id")
    public static final SqlColumn<Long> id = ruleScene.id;

    /**
     * Database Column Remarks:
     * 场景编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_code")
    public static final SqlColumn<String> sceneCode = ruleScene.sceneCode;

    /**
     * Database Column Remarks:
     * 场景名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_name")
    public static final SqlColumn<String> sceneName = ruleScene.sceneName;

    /**
     * Database Column Remarks:
     * 场景描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_desc")
    public static final SqlColumn<String> sceneDesc = ruleScene.sceneDesc;

    /**
     * Database Column Remarks:
     * 分组编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.group_code")
    public static final SqlColumn<String> groupCode = ruleScene.groupCode;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.yn")
    public static final SqlColumn<Boolean> yn = ruleScene.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.create_time")
    public static final SqlColumn<Date> createTime = ruleScene.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.modify_time")
    public static final SqlColumn<Date> modifyTime = ruleScene.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.creator")
    public static final SqlColumn<String> creator = ruleScene.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.modifier")
    public static final SqlColumn<String> modifier = ruleScene.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.tenant")
    public static final SqlColumn<String> tenant = ruleScene.tenant;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public static final class RuleScene extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> sceneCode = column("scene_code", JDBCType.VARCHAR);

        public final SqlColumn<String> sceneName = column("scene_name", JDBCType.VARCHAR);

        public final SqlColumn<String> sceneDesc = column("scene_desc", JDBCType.VARCHAR);

        public final SqlColumn<String> groupCode = column("group_code", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public RuleScene() {
            super("rule_scene");
        }
    }
}