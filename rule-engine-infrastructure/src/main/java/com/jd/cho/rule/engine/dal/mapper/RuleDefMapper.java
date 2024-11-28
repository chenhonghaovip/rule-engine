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

import static com.jd.cho.rule.engine.dal.mapper.RuleDefDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleDefMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    BasicColumn[] selectList = BasicColumn.columnList(id, priority, yn, createTime, modifyTime, creator, modifier, tenant, ruleCondition, ruleAction);

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
    @Insert({
            "${insertStatement}"
    })
    @Options(useGeneratedKeys = true, keyProperty = "records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<RuleDefDO> records);


    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertMultiple(MultiRowInsertStatementProvider<RuleDefDO> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleDefResult")
    Optional<RuleDefDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleDefResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
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
        return MyBatis3Utils.countFrom(this::count, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insert(RuleDefDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleDef, c ->
                c.map(id).toProperty("id")
                        .map(priority).toProperty("priority")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(ruleCondition).toProperty("ruleCondition")
                        .map(ruleAction).toProperty("ruleAction")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertMultiple(Collection<RuleDefDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleDef, c ->
                c.map(id).toProperty("id")
                        .map(priority).toProperty("priority")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(ruleCondition).toProperty("ruleCondition")
                        .map(ruleAction).toProperty("ruleAction")
        );
    }


    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertMultipleSelective(Collection<RuleDefDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleDef, c ->
                c.map(priority).toProperty("priority")
                        .map(creator).toProperty("creator")
                        .map(tenant).toProperty("tenant")
                        .map(ruleCondition).toProperty("ruleCondition")
                        .map(ruleAction).toProperty("ruleAction")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int insertSelective(RuleDefDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleDef, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(priority).toPropertyWhenPresent("priority", record::getPriority)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(ruleCondition).toPropertyWhenPresent("ruleCondition", record::getRuleCondition)
                        .map(ruleAction).toPropertyWhenPresent("ruleAction", record::getRuleAction)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default Optional<RuleDefDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default List<RuleDefDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default List<RuleDefDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default Optional<RuleDefDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleDef, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleDefDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(priority).equalTo(record::getPriority)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant)
                .set(ruleCondition).equalTo(record::getRuleCondition)
                .set(ruleAction).equalTo(record::getRuleAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleDefDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(priority).equalToWhenPresent(record::getPriority)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant)
                .set(ruleCondition).equalToWhenPresent(record::getRuleCondition)
                .set(ruleAction).equalToWhenPresent(record::getRuleAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int updateByPrimaryKey(RuleDefDO record) {
        return update(c ->
                c.set(priority).equalTo(record::getPriority)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .set(ruleCondition).equalTo(record::getRuleCondition)
                        .set(ruleAction).equalTo(record::getRuleAction)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    default int updateByPrimaryKeySelective(RuleDefDO record) {
        return update(c ->
                c.set(priority).equalToWhenPresent(record::getPriority)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .set(ruleCondition).equalToWhenPresent(record::getRuleCondition)
                        .set(ruleAction).equalToWhenPresent(record::getRuleAction)
                        .where(id, isEqualTo(record::getId))
        );
    }
}