package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RuleSceneActionDO;
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

import static com.jd.cho.rule.engine.dal.mapper.RuleSceneActionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleSceneActionMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    BasicColumn[] selectList = BasicColumn.columnList(id, sceneCode, actionCode, actionType, yn, createTime, modifyTime, creator, modifier, tenant, action);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RuleSceneActionDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RuleSceneActionDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleSceneActionResult")
    Optional<RuleSceneActionDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleSceneActionResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "scene_code", property = "sceneCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "action_code", property = "actionCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "action_type", property = "actionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR),
            @Result(column = "action", property = "action", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<RuleSceneActionDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int insert(RuleSceneActionDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleSceneAction, c ->
                c.map(id).toProperty("id")
                        .map(sceneCode).toProperty("sceneCode")
                        .map(actionCode).toProperty("actionCode")
                        .map(actionType).toProperty("actionType")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(action).toProperty("action")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int insertMultiple(Collection<RuleSceneActionDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleSceneAction, c ->
                c.map(id).toProperty("id")
                        .map(sceneCode).toProperty("sceneCode")
                        .map(actionCode).toProperty("actionCode")
                        .map(actionType).toProperty("actionType")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(action).toProperty("action")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int insertMultipleSelective(Collection<RuleSceneActionDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleSceneAction, c ->
                c.map(sceneCode).toProperty("sceneCode")
                        .map(actionCode).toProperty("actionCode")
                        .map(actionType).toProperty("actionType")
                        .map(creator).toProperty("creator")
                        .map(tenant).toProperty("tenant")
                        .map(action).toProperty("action")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int insertSelective(RuleSceneActionDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleSceneAction, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(sceneCode).toPropertyWhenPresent("sceneCode", record::getSceneCode)
                        .map(actionCode).toPropertyWhenPresent("actionCode", record::getActionCode)
                        .map(actionType).toPropertyWhenPresent("actionType", record::getActionType)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(action).toPropertyWhenPresent("action", record::getAction)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default Optional<RuleSceneActionDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default List<RuleSceneActionDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default List<RuleSceneActionDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default Optional<RuleSceneActionDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleSceneAction, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleSceneActionDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sceneCode).equalTo(record::getSceneCode)
                .set(actionCode).equalTo(record::getActionCode)
                .set(actionType).equalTo(record::getActionType)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant)
                .set(action).equalTo(record::getAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleSceneActionDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sceneCode).equalToWhenPresent(record::getSceneCode)
                .set(actionCode).equalToWhenPresent(record::getActionCode)
                .set(actionType).equalToWhenPresent(record::getActionType)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant)
                .set(action).equalToWhenPresent(record::getAction);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int updateByPrimaryKey(RuleSceneActionDO record) {
        return update(c ->
                c.set(sceneCode).equalTo(record::getSceneCode)
                        .set(actionCode).equalTo(record::getActionCode)
                        .set(actionType).equalTo(record::getActionType)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .set(action).equalTo(record::getAction)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene_action")
    default int updateByPrimaryKeySelective(RuleSceneActionDO record) {
        return update(c ->
                c.set(sceneCode).equalToWhenPresent(record::getSceneCode)
                        .set(actionCode).equalToWhenPresent(record::getActionCode)
                        .set(actionType).equalToWhenPresent(record::getActionType)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .set(action).equalToWhenPresent(record::getAction)
                        .where(id, isEqualTo(record::getId))
        );
    }
}