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

import static com.jd.cho.rule.engine.dal.mapper.RuleFactorGroupDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleFactorGroupMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    BasicColumn[] selectList = BasicColumn.columnList(id, groupCode, groupName, yn, createTime, modifyTime, creator, modifier, tenant, parentGroupCode);

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
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_group_code", property = "parentGroupCode", jdbcType = JdbcType.VARCHAR)
    })
    List<RuleFactorGroupDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insert(RuleFactorGroupDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleFactorGroup, c ->
                c.map(id).toProperty("id")
                        .map(groupCode).toProperty("groupCode")
                        .map(groupName).toProperty("groupName")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(parentGroupCode).toProperty("parentGroupCode")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insertMultiple(Collection<RuleFactorGroupDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleFactorGroup, c ->
                c.map(id).toProperty("id")
                        .map(groupCode).toProperty("groupCode")
                        .map(groupName).toProperty("groupName")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(parentGroupCode).toProperty("parentGroupCode")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int insertSelective(RuleFactorGroupDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleFactorGroup, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(groupCode).toPropertyWhenPresent("groupCode", record::getGroupCode)
                        .map(groupName).toPropertyWhenPresent("groupName", record::getGroupName)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(parentGroupCode).toPropertyWhenPresent("parentGroupCode", record::getParentGroupCode)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default Optional<RuleFactorGroupDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default List<RuleFactorGroupDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default List<RuleFactorGroupDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default Optional<RuleFactorGroupDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleFactorGroup, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleFactorGroupDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(groupCode).equalTo(record::getGroupCode)
                .set(groupName).equalTo(record::getGroupName)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant)
                .set(parentGroupCode).equalTo(record::getParentGroupCode);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleFactorGroupDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(groupCode).equalToWhenPresent(record::getGroupCode)
                .set(groupName).equalToWhenPresent(record::getGroupName)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant)
                .set(parentGroupCode).equalToWhenPresent(record::getParentGroupCode);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int updateByPrimaryKey(RuleFactorGroupDO record) {
        return update(c ->
                c.set(groupCode).equalTo(record::getGroupCode)
                        .set(groupName).equalTo(record::getGroupName)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .set(parentGroupCode).equalTo(record::getParentGroupCode)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    default int updateByPrimaryKeySelective(RuleFactorGroupDO record) {
        return update(c ->
                c.set(groupCode).equalToWhenPresent(record::getGroupCode)
                        .set(groupName).equalToWhenPresent(record::getGroupName)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .set(parentGroupCode).equalToWhenPresent(record::getParentGroupCode)
                        .where(id, isEqualTo(record::getId))
        );
    }
}