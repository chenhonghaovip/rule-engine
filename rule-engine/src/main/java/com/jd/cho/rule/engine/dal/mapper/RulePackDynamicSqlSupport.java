package com.jd.cho.rule.engine.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RulePackDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    public static final RulePack rulePack = new RulePack();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.id")
    public static final SqlColumn<Long> id = rulePack.id;

    /**
     * Database Column Remarks:
     * 规则包编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.rule_pack_code")
    public static final SqlColumn<String> rulePackCode = rulePack.rulePackCode;

    /**
     * Database Column Remarks:
     * 规则包名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.rule_pack_name")
    public static final SqlColumn<String> rulePackName = rulePack.rulePackName;

    /**
     * Database Column Remarks:
     * 规则包类型
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.rule_pack_type")
    public static final SqlColumn<String> rulePackType = rulePack.rulePackType;

    /**
     * Database Column Remarks:
     * 规则调度策略
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.rule_arrange_strategy")
    public static final SqlColumn<String> ruleArrangeStrategy = rulePack.ruleArrangeStrategy;

    /**
     * Database Column Remarks:
     * 规则ID集合
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.rule_ids")
    public static final SqlColumn<String> ruleIds = rulePack.ruleIds;

    /**
     * Database Column Remarks:
     * 版本号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.version")
    public static final SqlColumn<Integer> version = rulePack.version;

    /**
     * Database Column Remarks:
     * 是否最新版本（0:否，1:是）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.latest")
    public static final SqlColumn<Integer> latest = rulePack.latest;

    /**
     * Database Column Remarks:
     * 备注
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.remark")
    public static final SqlColumn<String> remark = rulePack.remark;

    /**
     * Database Column Remarks:
     * 状态（0:否，1:是）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.status")
    public static final SqlColumn<Boolean> status = rulePack.status;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.yn")
    public static final SqlColumn<Boolean> yn = rulePack.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.create_time")
    public static final SqlColumn<Date> createTime = rulePack.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.modify_time")
    public static final SqlColumn<Date> modifyTime = rulePack.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.creator")
    public static final SqlColumn<String> creator = rulePack.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.modifier")
    public static final SqlColumn<String> modifier = rulePack.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.tenant")
    public static final SqlColumn<String> tenant = rulePack.tenant;

    /**
     * Database Column Remarks:
     * 规则包入参
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_pack.pack_params")
    public static final SqlColumn<String> packParams = rulePack.packParams;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    public static final class RulePack extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> rulePackCode = column("rule_pack_code", JDBCType.VARCHAR);

        public final SqlColumn<String> rulePackName = column("rule_pack_name", JDBCType.VARCHAR);

        public final SqlColumn<String> rulePackType = column("rule_pack_type", JDBCType.VARCHAR);

        public final SqlColumn<String> ruleArrangeStrategy = column("rule_arrange_strategy", JDBCType.VARCHAR);

        public final SqlColumn<String> ruleIds = column("rule_ids", JDBCType.VARCHAR);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public final SqlColumn<Integer> latest = column("latest", JDBCType.INTEGER);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> status = column("`status`", JDBCType.BIT);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public final SqlColumn<String> packParams = column("pack_params", JDBCType.LONGVARCHAR);

        public RulePack() {
            super("rule_pack");
        }
    }
}