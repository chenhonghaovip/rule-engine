package com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper;

import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleSceneDO;
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

import static com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleSceneDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RuleSceneMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    BasicColumn[] selectList = BasicColumn.columnList(id, sceneCode, sceneName, sceneDesc, groupCode, yn, createTime, modifyTime, creator, modifier, tenant);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RuleSceneDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RuleSceneDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RuleSceneResult")
    Optional<RuleSceneDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RuleSceneResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "scene_code", property = "sceneCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scene_name", property = "sceneName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scene_desc", property = "sceneDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_code", property = "groupCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR)
    })
    List<RuleSceneDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insert(RuleSceneDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleScene, c ->
                c.map(id).toProperty("id")
                        .map(sceneCode).toProperty("sceneCode")
                        .map(sceneName).toProperty("sceneName")
                        .map(sceneDesc).toProperty("sceneDesc")
                        .map(groupCode).toProperty("groupCode")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insertMultiple(Collection<RuleSceneDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ruleScene, c ->
                c.map(id).toProperty("id")
                        .map(sceneCode).toProperty("sceneCode")
                        .map(sceneName).toProperty("sceneName")
                        .map(sceneDesc).toProperty("sceneDesc")
                        .map(groupCode).toProperty("groupCode")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insertSelective(RuleSceneDO record) {
        return MyBatis3Utils.insert(this::insert, record, ruleScene, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(sceneCode).toPropertyWhenPresent("sceneCode", record::getSceneCode)
                        .map(sceneName).toPropertyWhenPresent("sceneName", record::getSceneName)
                        .map(sceneDesc).toPropertyWhenPresent("sceneDesc", record::getSceneDesc)
                        .map(groupCode).toPropertyWhenPresent("groupCode", record::getGroupCode)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default Optional<RuleSceneDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default List<RuleSceneDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default List<RuleSceneDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default Optional<RuleSceneDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleSceneDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sceneCode).equalTo(record::getSceneCode)
                .set(sceneName).equalTo(record::getSceneName)
                .set(sceneDesc).equalTo(record::getSceneDesc)
                .set(groupCode).equalTo(record::getGroupCode)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleSceneDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sceneCode).equalToWhenPresent(record::getSceneCode)
                .set(sceneName).equalToWhenPresent(record::getSceneName)
                .set(sceneDesc).equalToWhenPresent(record::getSceneDesc)
                .set(groupCode).equalToWhenPresent(record::getGroupCode)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int updateByPrimaryKey(RuleSceneDO record) {
        return update(c ->
                c.set(sceneCode).equalTo(record::getSceneCode)
                        .set(sceneName).equalTo(record::getSceneName)
                        .set(sceneDesc).equalTo(record::getSceneDesc)
                        .set(groupCode).equalTo(record::getGroupCode)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int updateByPrimaryKeySelective(RuleSceneDO record) {
        return update(c ->
                c.set(sceneCode).equalToWhenPresent(record::getSceneCode)
                        .set(sceneName).equalToWhenPresent(record::getSceneName)
                        .set(sceneDesc).equalToWhenPresent(record::getSceneDesc)
                        .set(groupCode).equalToWhenPresent(record::getGroupCode)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .where(id, isEqualTo(record::getId))
        );
    }
}