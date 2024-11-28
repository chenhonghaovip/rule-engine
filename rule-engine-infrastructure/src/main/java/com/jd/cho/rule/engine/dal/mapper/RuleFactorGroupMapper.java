package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RuleFactorGroupDO;
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
public interface RuleFactorGroupMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    BasicColumn[] selectList = BasicColumn.columnList(RuleFactorGroupDynamicSqlSupport.id, RuleFactorGroupDynamicSqlSupport.groupCode, RuleFactorGroupDynamicSqlSupport.groupName, RuleFactorGroupDynamicSqlSupport.yn, RuleFactorGroupDynamicSqlSupport.createTime, RuleFactorGroupDynamicSqlSupport.modifyTime, RuleFactorGroupDynamicSqlSupport.creator, RuleFactorGroupDynamicSqlSupport.modifier, RuleFactorGroupDynamicSqlSupport.tenant);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RuleFactorGroupDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RuleFactorGroupDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleFactorGroupResult")
    Optional<RuleFactorGroupDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleFactorGroupResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "group_code", property = "groupCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_name", property = "groupName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR)
    })
    List<RuleFactorGroupDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(RuleFactorGroupDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insert(RuleFactorGroupDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, c ->
                c.map(RuleFactorGroupDynamicSqlSupport.id).toProperty("id")
                        .map(RuleFactorGroupDynamicSqlSupport.groupCode).toProperty("groupCode")
                        .map(RuleFactorGroupDynamicSqlSupport.groupName).toProperty("groupName")
                        .map(RuleFactorGroupDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleFactorGroupDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleFactorGroupDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleFactorGroupDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleFactorGroupDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleFactorGroupDynamicSqlSupport.tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insertMultiple(Collection<RuleFactorGroupDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, c ->
                c.map(RuleFactorGroupDynamicSqlSupport.id).toProperty("id")
                        .map(RuleFactorGroupDynamicSqlSupport.groupCode).toProperty("groupCode")
                        .map(RuleFactorGroupDynamicSqlSupport.groupName).toProperty("groupName")
                        .map(RuleFactorGroupDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleFactorGroupDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleFactorGroupDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleFactorGroupDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleFactorGroupDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleFactorGroupDynamicSqlSupport.tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insertSelective(RuleFactorGroupDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, c ->
                c.map(RuleFactorGroupDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
                        .map(RuleFactorGroupDynamicSqlSupport.groupCode).toPropertyWhenPresent("groupCode", record::getGroupCode)
                        .map(RuleFactorGroupDynamicSqlSupport.groupName).toPropertyWhenPresent("groupName", record::getGroupName)
                        .map(RuleFactorGroupDynamicSqlSupport.yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(RuleFactorGroupDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(RuleFactorGroupDynamicSqlSupport.modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(RuleFactorGroupDynamicSqlSupport.creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(RuleFactorGroupDynamicSqlSupport.modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(RuleFactorGroupDynamicSqlSupport.tenant).toPropertyWhenPresent("tenant", record::getTenant)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default Optional<RuleFactorGroupDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default List<RuleFactorGroupDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default List<RuleFactorGroupDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default Optional<RuleFactorGroupDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(RuleFactorGroupDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, RuleFactorGroupDynamicSqlSupport.ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleFactorGroupDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleFactorGroupDynamicSqlSupport.id).equalTo(record::getId)
                .set(RuleFactorGroupDynamicSqlSupport.groupCode).equalTo(record::getGroupCode)
                .set(RuleFactorGroupDynamicSqlSupport.groupName).equalTo(record::getGroupName)
                .set(RuleFactorGroupDynamicSqlSupport.yn).equalTo(record::getYn)
                .set(RuleFactorGroupDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(RuleFactorGroupDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                .set(RuleFactorGroupDynamicSqlSupport.creator).equalTo(record::getCreator)
                .set(RuleFactorGroupDynamicSqlSupport.modifier).equalTo(record::getModifier)
                .set(RuleFactorGroupDynamicSqlSupport.tenant).equalTo(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleFactorGroupDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleFactorGroupDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(RuleFactorGroupDynamicSqlSupport.groupCode).equalToWhenPresent(record::getGroupCode)
                .set(RuleFactorGroupDynamicSqlSupport.groupName).equalToWhenPresent(record::getGroupName)
                .set(RuleFactorGroupDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                .set(RuleFactorGroupDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(RuleFactorGroupDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(RuleFactorGroupDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                .set(RuleFactorGroupDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                .set(RuleFactorGroupDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int updateByPrimaryKey(RuleFactorGroupDO record) {
        return update(c ->
                c.set(RuleFactorGroupDynamicSqlSupport.groupCode).equalTo(record::getGroupCode)
                        .set(RuleFactorGroupDynamicSqlSupport.groupName).equalTo(record::getGroupName)
                        .set(RuleFactorGroupDynamicSqlSupport.yn).equalTo(record::getYn)
                        .set(RuleFactorGroupDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                        .set(RuleFactorGroupDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                        .set(RuleFactorGroupDynamicSqlSupport.creator).equalTo(record::getCreator)
                        .set(RuleFactorGroupDynamicSqlSupport.modifier).equalTo(record::getModifier)
                        .set(RuleFactorGroupDynamicSqlSupport.tenant).equalTo(record::getTenant)
                        .where(RuleFactorGroupDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int updateByPrimaryKeySelective(RuleFactorGroupDO record) {
        return update(c ->
                c.set(RuleFactorGroupDynamicSqlSupport.groupCode).equalToWhenPresent(record::getGroupCode)
                        .set(RuleFactorGroupDynamicSqlSupport.groupName).equalToWhenPresent(record::getGroupName)
                        .set(RuleFactorGroupDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                        .set(RuleFactorGroupDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                        .set(RuleFactorGroupDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(RuleFactorGroupDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                        .set(RuleFactorGroupDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                        .set(RuleFactorGroupDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant)
                        .where(RuleFactorGroupDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}