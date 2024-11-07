package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RuleDefDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleDefMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    BasicColumn[] selectList = BasicColumn.columnList(RuleDefDynamicSqlSupport.id, RuleDefDynamicSqlSupport.ruleCode, RuleDefDynamicSqlSupport.ruleName, RuleDefDynamicSqlSupport.version, RuleDefDynamicSqlSupport.latest, RuleDefDynamicSqlSupport.priority, RuleDefDynamicSqlSupport.remark, RuleDefDynamicSqlSupport.status, RuleDefDynamicSqlSupport.yn, RuleDefDynamicSqlSupport.createTime, RuleDefDynamicSqlSupport.modifyTime, RuleDefDynamicSqlSupport.creator, RuleDefDynamicSqlSupport.modifier, RuleDefDynamicSqlSupport.tenant, RuleDefDynamicSqlSupport.ruleCondition, RuleDefDynamicSqlSupport.ruleAction);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RuleDefDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RuleDefDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleDefResult")
    Optional<RuleDefDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleDefResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "rule_code", property = "ruleCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_name", property = "ruleName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
            @Result(column = "latest", property = "latest", jdbcType = JdbcType.INTEGER),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.BIT),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_condition", property = "ruleCondition", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "rule_action", property = "ruleAction", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<RuleDefDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(RuleDefDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insert(RuleDefDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleDefDynamicSqlSupport.ruleDef, c ->
                c.map(RuleDefDynamicSqlSupport.id).toProperty("id")
                        .map(RuleDefDynamicSqlSupport.ruleCode).toProperty("ruleCode")
                        .map(RuleDefDynamicSqlSupport.ruleName).toProperty("ruleName")
                        .map(RuleDefDynamicSqlSupport.version).toProperty("version")
                        .map(RuleDefDynamicSqlSupport.latest).toProperty("latest")
                        .map(RuleDefDynamicSqlSupport.priority).toProperty("priority")
                        .map(RuleDefDynamicSqlSupport.remark).toProperty("remark")
                        .map(RuleDefDynamicSqlSupport.status).toProperty("status")
                        .map(RuleDefDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleDefDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleDefDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleDefDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleDefDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleDefDynamicSqlSupport.tenant).toProperty("tenant")
                        .map(RuleDefDynamicSqlSupport.ruleCondition).toProperty("ruleCondition")
                        .map(RuleDefDynamicSqlSupport.ruleAction).toProperty("ruleAction")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertMultiple(Collection<RuleDefDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, RuleDefDynamicSqlSupport.ruleDef, c ->
                c.map(RuleDefDynamicSqlSupport.id).toProperty("id")
                        .map(RuleDefDynamicSqlSupport.ruleCode).toProperty("ruleCode")
                        .map(RuleDefDynamicSqlSupport.ruleName).toProperty("ruleName")
                        .map(RuleDefDynamicSqlSupport.version).toProperty("version")
                        .map(RuleDefDynamicSqlSupport.latest).toProperty("latest")
                        .map(RuleDefDynamicSqlSupport.priority).toProperty("priority")
                        .map(RuleDefDynamicSqlSupport.remark).toProperty("remark")
                        .map(RuleDefDynamicSqlSupport.status).toProperty("status")
                        .map(RuleDefDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleDefDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleDefDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleDefDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleDefDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleDefDynamicSqlSupport.tenant).toProperty("tenant")
                        .map(RuleDefDynamicSqlSupport.ruleCondition).toProperty("ruleCondition")
                        .map(RuleDefDynamicSqlSupport.ruleAction).toProperty("ruleAction")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertSelective(RuleDefDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleDefDynamicSqlSupport.ruleDef, c ->
                c.map(RuleDefDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
                        .map(RuleDefDynamicSqlSupport.ruleCode).toPropertyWhenPresent("ruleCode", record::getRuleCode)
                        .map(RuleDefDynamicSqlSupport.ruleName).toPropertyWhenPresent("ruleName", record::getRuleName)
                        .map(RuleDefDynamicSqlSupport.version).toPropertyWhenPresent("version", record::getVersion)
                        .map(RuleDefDynamicSqlSupport.latest).toPropertyWhenPresent("latest", record::getLatest)
                        .map(RuleDefDynamicSqlSupport.priority).toPropertyWhenPresent("priority", record::getPriority)
                        .map(RuleDefDynamicSqlSupport.remark).toPropertyWhenPresent("remark", record::getRemark)
                        .map(RuleDefDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
                        .map(RuleDefDynamicSqlSupport.yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(RuleDefDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(RuleDefDynamicSqlSupport.modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(RuleDefDynamicSqlSupport.creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(RuleDefDynamicSqlSupport.modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(RuleDefDynamicSqlSupport.tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(RuleDefDynamicSqlSupport.ruleCondition).toPropertyWhenPresent("ruleCondition", record::getRuleCondition)
                        .map(RuleDefDynamicSqlSupport.ruleAction).toPropertyWhenPresent("ruleAction", record::getRuleAction)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default Optional<RuleDefDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default List<RuleDefDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default List<RuleDefDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default Optional<RuleDefDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(RuleDefDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, RuleDefDynamicSqlSupport.ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleDefDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleDefDynamicSqlSupport.id).equalTo(record::getId)
                .set(RuleDefDynamicSqlSupport.ruleCode).equalTo(record::getRuleCode)
                .set(RuleDefDynamicSqlSupport.ruleName).equalTo(record::getRuleName)
                .set(RuleDefDynamicSqlSupport.version).equalTo(record::getVersion)
                .set(RuleDefDynamicSqlSupport.latest).equalTo(record::getLatest)
                .set(RuleDefDynamicSqlSupport.priority).equalTo(record::getPriority)
                .set(RuleDefDynamicSqlSupport.remark).equalTo(record::getRemark)
                .set(RuleDefDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(RuleDefDynamicSqlSupport.yn).equalTo(record::getYn)
                .set(RuleDefDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(RuleDefDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                .set(RuleDefDynamicSqlSupport.creator).equalTo(record::getCreator)
                .set(RuleDefDynamicSqlSupport.modifier).equalTo(record::getModifier)
                .set(RuleDefDynamicSqlSupport.tenant).equalTo(record::getTenant)
                .set(RuleDefDynamicSqlSupport.ruleCondition).equalTo(record::getRuleCondition)
                .set(RuleDefDynamicSqlSupport.ruleAction).equalTo(record::getRuleAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleDefDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleDefDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(RuleDefDynamicSqlSupport.ruleCode).equalToWhenPresent(record::getRuleCode)
                .set(RuleDefDynamicSqlSupport.ruleName).equalToWhenPresent(record::getRuleName)
                .set(RuleDefDynamicSqlSupport.version).equalToWhenPresent(record::getVersion)
                .set(RuleDefDynamicSqlSupport.latest).equalToWhenPresent(record::getLatest)
                .set(RuleDefDynamicSqlSupport.priority).equalToWhenPresent(record::getPriority)
                .set(RuleDefDynamicSqlSupport.remark).equalToWhenPresent(record::getRemark)
                .set(RuleDefDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(RuleDefDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                .set(RuleDefDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(RuleDefDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(RuleDefDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                .set(RuleDefDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                .set(RuleDefDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant)
                .set(RuleDefDynamicSqlSupport.ruleCondition).equalToWhenPresent(record::getRuleCondition)
                .set(RuleDefDynamicSqlSupport.ruleAction).equalToWhenPresent(record::getRuleAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int updateByPrimaryKey(RuleDefDO record) {
        return update(c ->
                c.set(RuleDefDynamicSqlSupport.ruleCode).equalTo(record::getRuleCode)
                        .set(RuleDefDynamicSqlSupport.ruleName).equalTo(record::getRuleName)
                        .set(RuleDefDynamicSqlSupport.version).equalTo(record::getVersion)
                        .set(RuleDefDynamicSqlSupport.latest).equalTo(record::getLatest)
                        .set(RuleDefDynamicSqlSupport.priority).equalTo(record::getPriority)
                        .set(RuleDefDynamicSqlSupport.remark).equalTo(record::getRemark)
                        .set(RuleDefDynamicSqlSupport.status).equalTo(record::getStatus)
                        .set(RuleDefDynamicSqlSupport.yn).equalTo(record::getYn)
                        .set(RuleDefDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                        .set(RuleDefDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                        .set(RuleDefDynamicSqlSupport.creator).equalTo(record::getCreator)
                        .set(RuleDefDynamicSqlSupport.modifier).equalTo(record::getModifier)
                        .set(RuleDefDynamicSqlSupport.tenant).equalTo(record::getTenant)
                        .set(RuleDefDynamicSqlSupport.ruleCondition).equalTo(record::getRuleCondition)
                        .set(RuleDefDynamicSqlSupport.ruleAction).equalTo(record::getRuleAction)
                        .where(RuleDefDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int updateByPrimaryKeySelective(RuleDefDO record) {
        return update(c ->
                c.set(RuleDefDynamicSqlSupport.ruleCode).equalToWhenPresent(record::getRuleCode)
                        .set(RuleDefDynamicSqlSupport.ruleName).equalToWhenPresent(record::getRuleName)
                        .set(RuleDefDynamicSqlSupport.version).equalToWhenPresent(record::getVersion)
                        .set(RuleDefDynamicSqlSupport.latest).equalToWhenPresent(record::getLatest)
                        .set(RuleDefDynamicSqlSupport.priority).equalToWhenPresent(record::getPriority)
                        .set(RuleDefDynamicSqlSupport.remark).equalToWhenPresent(record::getRemark)
                        .set(RuleDefDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                        .set(RuleDefDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                        .set(RuleDefDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                        .set(RuleDefDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(RuleDefDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                        .set(RuleDefDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                        .set(RuleDefDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant)
                        .set(RuleDefDynamicSqlSupport.ruleCondition).equalToWhenPresent(record::getRuleCondition)
                        .set(RuleDefDynamicSqlSupport.ruleAction).equalToWhenPresent(record::getRuleAction)
                        .where(RuleDefDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}