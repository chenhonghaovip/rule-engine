package com.jd.cho.rule.engine.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RuleFactorDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    public static final RuleFactor ruleFactor = new RuleFactor();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.id")
    public static final SqlColumn<Long> id = ruleFactor.id;

    /**
     * Database Column Remarks:
     * 因子编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_code")
    public static final SqlColumn<String> factorCode = ruleFactor.factorCode;

    /**
     * Database Column Remarks:
     * 因子全编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_full_code")
    public static final SqlColumn<String> factorFullCode = ruleFactor.factorFullCode;

    /**
     * Database Column Remarks:
     * 因子名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_name")
    public static final SqlColumn<String> factorName = ruleFactor.factorName;

    /**
     * Database Column Remarks:
     * 分组编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.group_code")
    public static final SqlColumn<String> groupCode = ruleFactor.groupCode;

    /**
     * Database Column Remarks:
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_type")
    public static final SqlColumn<String> factorType = ruleFactor.factorType;

    /**
     * Database Column Remarks:
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.constant_type")
    public static final SqlColumn<String> constantType = ruleFactor.constantType;

    /**
     * Database Column Remarks:
     * 常量值
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.constant_value")
    public static final SqlColumn<String> constantValue = ruleFactor.constantValue;

    /**
     * Database Column Remarks:
     * 脚本参数
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_script_param")
    public static final SqlColumn<String> factorScriptParam = ruleFactor.factorScriptParam;

    /**
     * Database Column Remarks:
     * 备注
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.remark")
    public static final SqlColumn<String> remark = ruleFactor.remark;

    /**
     * Database Column Remarks:
     * 状态（0:否，1:是）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.status")
    public static final SqlColumn<Boolean> status = ruleFactor.status;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.yn")
    public static final SqlColumn<Boolean> yn = ruleFactor.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.create_time")
    public static final SqlColumn<Date> createTime = ruleFactor.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.modify_time")
    public static final SqlColumn<Date> modifyTime = ruleFactor.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.creator")
    public static final SqlColumn<String> creator = ruleFactor.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.modifier")
    public static final SqlColumn<String> modifier = ruleFactor.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.tenant")
    public static final SqlColumn<String> tenant = ruleFactor.tenant;

    /**
     * Database Column Remarks:
     * 脚本代码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor.factor_script")
    public static final SqlColumn<String> factorScript = ruleFactor.factorScript;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    public static final class RuleFactor extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> factorCode = column("factor_code", JDBCType.VARCHAR);

        public final SqlColumn<String> factorFullCode = column("factor_full_code", JDBCType.VARCHAR);

        public final SqlColumn<String> factorName = column("factor_name", JDBCType.VARCHAR);

        public final SqlColumn<String> groupCode = column("group_code", JDBCType.VARCHAR);

        public final SqlColumn<String> factorType = column("factor_type", JDBCType.VARCHAR);

        public final SqlColumn<String> constantType = column("constant_type", JDBCType.VARCHAR);

        public final SqlColumn<String> constantValue = column("constant_value", JDBCType.VARCHAR);

        public final SqlColumn<String> factorScriptParam = column("factor_script_param", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> status = column("`status`", JDBCType.BIT);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public final SqlColumn<String> factorScript = column("factor_script", JDBCType.LONGVARCHAR);

        public RuleFactor() {
            super("rule_factor");
        }
    }
}