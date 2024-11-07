package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
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
public interface RuleSceneMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    BasicColumn[] selectList = BasicColumn.columnList(RuleSceneDynamicSqlSupport.id, RuleSceneDynamicSqlSupport.sceneCode, RuleSceneDynamicSqlSupport.sceneName, RuleSceneDynamicSqlSupport.sceneDesc, RuleSceneDynamicSqlSupport.groupCode, RuleSceneDynamicSqlSupport.yn, RuleSceneDynamicSqlSupport.createTime, RuleSceneDynamicSqlSupport.modifyTime, RuleSceneDynamicSqlSupport.creator, RuleSceneDynamicSqlSupport.modifier, RuleSceneDynamicSqlSupport.tenant);

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
        return MyBatis3Utils.countFrom(this::count, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(RuleSceneDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insert(RuleSceneDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleSceneDynamicSqlSupport.ruleScene, c ->
                c.map(RuleSceneDynamicSqlSupport.id).toProperty("id")
                        .map(RuleSceneDynamicSqlSupport.sceneCode).toProperty("sceneCode")
                        .map(RuleSceneDynamicSqlSupport.sceneName).toProperty("sceneName")
                        .map(RuleSceneDynamicSqlSupport.sceneDesc).toProperty("sceneDesc")
                        .map(RuleSceneDynamicSqlSupport.groupCode).toProperty("groupCode")
                        .map(RuleSceneDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleSceneDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleSceneDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleSceneDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleSceneDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleSceneDynamicSqlSupport.tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insertMultiple(Collection<RuleSceneDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, RuleSceneDynamicSqlSupport.ruleScene, c ->
                c.map(RuleSceneDynamicSqlSupport.id).toProperty("id")
                        .map(RuleSceneDynamicSqlSupport.sceneCode).toProperty("sceneCode")
                        .map(RuleSceneDynamicSqlSupport.sceneName).toProperty("sceneName")
                        .map(RuleSceneDynamicSqlSupport.sceneDesc).toProperty("sceneDesc")
                        .map(RuleSceneDynamicSqlSupport.groupCode).toProperty("groupCode")
                        .map(RuleSceneDynamicSqlSupport.yn).toProperty("yn")
                        .map(RuleSceneDynamicSqlSupport.createTime).toProperty("createTime")
                        .map(RuleSceneDynamicSqlSupport.modifyTime).toProperty("modifyTime")
                        .map(RuleSceneDynamicSqlSupport.creator).toProperty("creator")
                        .map(RuleSceneDynamicSqlSupport.modifier).toProperty("modifier")
                        .map(RuleSceneDynamicSqlSupport.tenant).toProperty("tenant")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int insertSelective(RuleSceneDO record) {
        return MyBatis3Utils.insert(this::insert, record, RuleSceneDynamicSqlSupport.ruleScene, c ->
                c.map(RuleSceneDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
                        .map(RuleSceneDynamicSqlSupport.sceneCode).toPropertyWhenPresent("sceneCode", record::getSceneCode)
                        .map(RuleSceneDynamicSqlSupport.sceneName).toPropertyWhenPresent("sceneName", record::getSceneName)
                        .map(RuleSceneDynamicSqlSupport.sceneDesc).toPropertyWhenPresent("sceneDesc", record::getSceneDesc)
                        .map(RuleSceneDynamicSqlSupport.groupCode).toPropertyWhenPresent("groupCode", record::getGroupCode)
                        .map(RuleSceneDynamicSqlSupport.yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(RuleSceneDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(RuleSceneDynamicSqlSupport.modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(RuleSceneDynamicSqlSupport.creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(RuleSceneDynamicSqlSupport.modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(RuleSceneDynamicSqlSupport.tenant).toPropertyWhenPresent("tenant", record::getTenant)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default Optional<RuleSceneDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default List<RuleSceneDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default List<RuleSceneDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default Optional<RuleSceneDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(RuleSceneDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, RuleSceneDynamicSqlSupport.ruleScene, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleSceneDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleSceneDynamicSqlSupport.id).equalTo(record::getId)
                .set(RuleSceneDynamicSqlSupport.sceneCode).equalTo(record::getSceneCode)
                .set(RuleSceneDynamicSqlSupport.sceneName).equalTo(record::getSceneName)
                .set(RuleSceneDynamicSqlSupport.sceneDesc).equalTo(record::getSceneDesc)
                .set(RuleSceneDynamicSqlSupport.groupCode).equalTo(record::getGroupCode)
                .set(RuleSceneDynamicSqlSupport.yn).equalTo(record::getYn)
                .set(RuleSceneDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(RuleSceneDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                .set(RuleSceneDynamicSqlSupport.creator).equalTo(record::getCreator)
                .set(RuleSceneDynamicSqlSupport.modifier).equalTo(record::getModifier)
                .set(RuleSceneDynamicSqlSupport.tenant).equalTo(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleSceneDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(RuleSceneDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(RuleSceneDynamicSqlSupport.sceneCode).equalToWhenPresent(record::getSceneCode)
                .set(RuleSceneDynamicSqlSupport.sceneName).equalToWhenPresent(record::getSceneName)
                .set(RuleSceneDynamicSqlSupport.sceneDesc).equalToWhenPresent(record::getSceneDesc)
                .set(RuleSceneDynamicSqlSupport.groupCode).equalToWhenPresent(record::getGroupCode)
                .set(RuleSceneDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                .set(RuleSceneDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(RuleSceneDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(RuleSceneDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                .set(RuleSceneDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                .set(RuleSceneDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int updateByPrimaryKey(RuleSceneDO record) {
        return update(c ->
                c.set(RuleSceneDynamicSqlSupport.sceneCode).equalTo(record::getSceneCode)
                        .set(RuleSceneDynamicSqlSupport.sceneName).equalTo(record::getSceneName)
                        .set(RuleSceneDynamicSqlSupport.sceneDesc).equalTo(record::getSceneDesc)
                        .set(RuleSceneDynamicSqlSupport.groupCode).equalTo(record::getGroupCode)
                        .set(RuleSceneDynamicSqlSupport.yn).equalTo(record::getYn)
                        .set(RuleSceneDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                        .set(RuleSceneDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
                        .set(RuleSceneDynamicSqlSupport.creator).equalTo(record::getCreator)
                        .set(RuleSceneDynamicSqlSupport.modifier).equalTo(record::getModifier)
                        .set(RuleSceneDynamicSqlSupport.tenant).equalTo(record::getTenant)
                        .where(RuleSceneDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    default int updateByPrimaryKeySelective(RuleSceneDO record) {
        return update(c ->
                c.set(RuleSceneDynamicSqlSupport.sceneCode).equalToWhenPresent(record::getSceneCode)
                        .set(RuleSceneDynamicSqlSupport.sceneName).equalToWhenPresent(record::getSceneName)
                        .set(RuleSceneDynamicSqlSupport.sceneDesc).equalToWhenPresent(record::getSceneDesc)
                        .set(RuleSceneDynamicSqlSupport.groupCode).equalToWhenPresent(record::getGroupCode)
                        .set(RuleSceneDynamicSqlSupport.yn).equalToWhenPresent(record::getYn)
                        .set(RuleSceneDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                        .set(RuleSceneDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(RuleSceneDynamicSqlSupport.creator).equalToWhenPresent(record::getCreator)
                        .set(RuleSceneDynamicSqlSupport.modifier).equalToWhenPresent(record::getModifier)
                        .set(RuleSceneDynamicSqlSupport.tenant).equalToWhenPresent(record::getTenant)
                        .where(RuleSceneDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}