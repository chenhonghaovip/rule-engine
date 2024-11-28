package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
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

import static com.jd.cho.rule.engine.dal.mapper.RuleFactorDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleFactorMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    BasicColumn[] selectList = BasicColumn.columnList(id, factorCode, factorName, groupCode, factorType, constantType, factorScriptParam, remark, status, yn, createTime, modifyTime, creator, modifier, tenant, constantValue, factorScript, extInfo);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RuleFactorDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RuleFactorDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleFactorResult")
    Optional<RuleFactorDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleFactorResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "factor_code", property = "factorCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "factor_name", property = "factorName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_code", property = "groupCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "factor_type", property = "factorType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "constant_type", property = "constantType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "factor_script_param", property = "factorScriptParam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.BIT),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR),
            @Result(column = "constant_value", property = "constantValue", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "factor_script", property = "factorScript", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "ext_info", property = "extInfo", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<RuleFactorDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int insert(RuleFactorDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleFactor, c ->
                c.map(id).toProperty("id")
                        .map(factorCode).toProperty("factorCode")
                        .map(factorName).toProperty("factorName")
                        .map(groupCode).toProperty("groupCode")
                        .map(factorType).toProperty("factorType")
                        .map(constantType).toProperty("constantType")
                        .map(factorScriptParam).toProperty("factorScriptParam")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(constantValue).toProperty("constantValue")
                        .map(factorScript).toProperty("factorScript")
                        .map(extInfo).toProperty("extInfo")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int insertMultiple(Collection<RuleFactorDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleFactor, c ->
                c.map(id).toProperty("id")
                        .map(factorCode).toProperty("factorCode")
                        .map(factorName).toProperty("factorName")
                        .map(groupCode).toProperty("groupCode")
                        .map(factorType).toProperty("factorType")
                        .map(constantType).toProperty("constantType")
                        .map(factorScriptParam).toProperty("factorScriptParam")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(constantValue).toProperty("constantValue")
                        .map(factorScript).toProperty("factorScript")
                        .map(extInfo).toProperty("extInfo")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int insertSelective(RuleFactorDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleFactor, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(factorCode).toPropertyWhenPresent("factorCode", record::getFactorCode)
                        .map(factorName).toPropertyWhenPresent("factorName", record::getFactorName)
                        .map(groupCode).toPropertyWhenPresent("groupCode", record::getGroupCode)
                        .map(factorType).toPropertyWhenPresent("factorType", record::getFactorType)
                        .map(constantType).toPropertyWhenPresent("constantType", record::getConstantType)
                        .map(factorScriptParam).toPropertyWhenPresent("factorScriptParam", record::getFactorScriptParam)
                        .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(constantValue).toPropertyWhenPresent("constantValue", record::getConstantValue)
                        .map(factorScript).toPropertyWhenPresent("factorScript", record::getFactorScript)
                        .map(extInfo).toPropertyWhenPresent("extInfo", record::getExtInfo)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default Optional<RuleFactorDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default List<RuleFactorDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default List<RuleFactorDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default Optional<RuleFactorDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleFactor, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleFactorDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(factorCode).equalTo(record::getFactorCode)
                .set(factorName).equalTo(record::getFactorName)
                .set(groupCode).equalTo(record::getGroupCode)
                .set(factorType).equalTo(record::getFactorType)
                .set(constantType).equalTo(record::getConstantType)
                .set(factorScriptParam).equalTo(record::getFactorScriptParam)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant)
                .set(constantValue).equalTo(record::getConstantValue)
                .set(factorScript).equalTo(record::getFactorScript)
                .set(extInfo).equalTo(record::getExtInfo);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleFactorDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(factorCode).equalToWhenPresent(record::getFactorCode)
                .set(factorName).equalToWhenPresent(record::getFactorName)
                .set(groupCode).equalToWhenPresent(record::getGroupCode)
                .set(factorType).equalToWhenPresent(record::getFactorType)
                .set(constantType).equalToWhenPresent(record::getConstantType)
                .set(factorScriptParam).equalToWhenPresent(record::getFactorScriptParam)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant)
                .set(constantValue).equalToWhenPresent(record::getConstantValue)
                .set(factorScript).equalToWhenPresent(record::getFactorScript)
                .set(extInfo).equalToWhenPresent(record::getExtInfo);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int updateByPrimaryKey(RuleFactorDO record) {
        return update(c ->
                c.set(factorCode).equalTo(record::getFactorCode)
                        .set(factorName).equalTo(record::getFactorName)
                        .set(groupCode).equalTo(record::getGroupCode)
                        .set(factorType).equalTo(record::getFactorType)
                        .set(constantType).equalTo(record::getConstantType)
                        .set(factorScriptParam).equalTo(record::getFactorScriptParam)
                        .set(remark).equalTo(record::getRemark)
                        .set(status).equalTo(record::getStatus)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .set(constantValue).equalTo(record::getConstantValue)
                        .set(factorScript).equalTo(record::getFactorScript)
                        .set(extInfo).equalTo(record::getExtInfo)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor")
    default int updateByPrimaryKeySelective(RuleFactorDO record) {
        return update(c ->
                c.set(factorCode).equalToWhenPresent(record::getFactorCode)
                        .set(factorName).equalToWhenPresent(record::getFactorName)
                        .set(groupCode).equalToWhenPresent(record::getGroupCode)
                        .set(factorType).equalToWhenPresent(record::getFactorType)
                        .set(constantType).equalToWhenPresent(record::getConstantType)
                        .set(factorScriptParam).equalToWhenPresent(record::getFactorScriptParam)
                        .set(remark).equalToWhenPresent(record::getRemark)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .set(constantValue).equalToWhenPresent(record::getConstantValue)
                        .set(factorScript).equalToWhenPresent(record::getFactorScript)
                        .set(extInfo).equalToWhenPresent(record::getExtInfo)
                        .where(id, isEqualTo(record::getId))
        );
    }
}