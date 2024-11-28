package com.jd.cho.rule.engine.dal.mapper;

import com.jd.cho.rule.engine.dal.DO.RulePackDO;
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

import static com.jd.cho.rule.engine.dal.mapper.RulePackDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RulePackMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    BasicColumn[] selectList = BasicColumn.columnList(id, rulePackCode, rulePackName, rulePackType, ruleArrangeStrategy, ruleIds, version, latest, remark, status, yn, createTime, modifyTime, creator, modifier, tenant, packParams, ruleContent);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RulePackDO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RulePackDO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RulePackResult")
    Optional<RulePackDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RulePackResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "rule_pack_code", property = "rulePackCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_pack_name", property = "rulePackName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_pack_type", property = "rulePackType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_arrange_strategy", property = "ruleArrangeStrategy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rule_ids", property = "ruleIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
            @Result(column = "latest", property = "latest", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.BIT),
            @Result(column = "yn", property = "yn", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tenant", property = "tenant", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pack_params", property = "packParams", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "rule_content", property = "ruleContent", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<RulePackDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int insert(RulePackDO record) {
        return MyBatis3Utils.insert(this::insert, record, rulePack, c ->
                c.map(id).toProperty("id")
                        .map(rulePackCode).toProperty("rulePackCode")
                        .map(rulePackName).toProperty("rulePackName")
                        .map(rulePackType).toProperty("rulePackType")
                        .map(ruleArrangeStrategy).toProperty("ruleArrangeStrategy")
                        .map(ruleIds).toProperty("ruleIds")
                        .map(version).toProperty("version")
                        .map(latest).toProperty("latest")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(packParams).toProperty("packParams")
                        .map(ruleContent).toProperty("ruleContent")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int insertMultiple(Collection<RulePackDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rulePack, c ->
                c.map(id).toProperty("id")
                        .map(rulePackCode).toProperty("rulePackCode")
                        .map(rulePackName).toProperty("rulePackName")
                        .map(rulePackType).toProperty("rulePackType")
                        .map(ruleArrangeStrategy).toProperty("ruleArrangeStrategy")
                        .map(ruleIds).toProperty("ruleIds")
                        .map(version).toProperty("version")
                        .map(latest).toProperty("latest")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(yn).toProperty("yn")
                        .map(createTime).toProperty("createTime")
                        .map(modifyTime).toProperty("modifyTime")
                        .map(creator).toProperty("creator")
                        .map(modifier).toProperty("modifier")
                        .map(tenant).toProperty("tenant")
                        .map(packParams).toProperty("packParams")
                        .map(ruleContent).toProperty("ruleContent")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int insertSelective(RulePackDO record) {
        return MyBatis3Utils.insert(this::insert, record, rulePack, c ->
                c.map(rulePackCode).toPropertyWhenPresent("rulePackCode", record::getRulePackCode)
                        .map(rulePackName).toPropertyWhenPresent("rulePackName", record::getRulePackName)
                        .map(rulePackType).toPropertyWhenPresent("rulePackType", record::getRulePackType)
                        .map(ruleArrangeStrategy).toPropertyWhenPresent("ruleArrangeStrategy", record::getRuleArrangeStrategy)
                        .map(ruleIds).toPropertyWhenPresent("ruleIds", record::getRuleIds)
                        .map(version).toPropertyWhenPresent("version", record::getVersion)
                        .map(latest).toPropertyWhenPresent("latest", record::getLatest)
                        .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(yn).toPropertyWhenPresent("yn", record::getYn)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(tenant).toPropertyWhenPresent("tenant", record::getTenant)
                        .map(packParams).toPropertyWhenPresent("packParams", record::getPackParams)
                        .map(ruleContent).toPropertyWhenPresent("ruleContent", record::getRuleContent)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default Optional<RulePackDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default List<RulePackDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default List<RulePackDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default Optional<RulePackDO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rulePack, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    static UpdateDSL<UpdateModel> updateAllColumns(RulePackDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(rulePackCode).equalTo(record::getRulePackCode)
                .set(rulePackName).equalTo(record::getRulePackName)
                .set(rulePackType).equalTo(record::getRulePackType)
                .set(ruleArrangeStrategy).equalTo(record::getRuleArrangeStrategy)
                .set(ruleIds).equalTo(record::getRuleIds)
                .set(version).equalTo(record::getVersion)
                .set(latest).equalTo(record::getLatest)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .set(yn).equalTo(record::getYn)
                .set(createTime).equalTo(record::getCreateTime)
                .set(modifyTime).equalTo(record::getModifyTime)
                .set(creator).equalTo(record::getCreator)
                .set(modifier).equalTo(record::getModifier)
                .set(tenant).equalTo(record::getTenant)
                .set(packParams).equalTo(record::getPackParams)
                .set(ruleContent).equalTo(record::getRuleContent);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RulePackDO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(rulePackCode).equalToWhenPresent(record::getRulePackCode)
                .set(rulePackName).equalToWhenPresent(record::getRulePackName)
                .set(rulePackType).equalToWhenPresent(record::getRulePackType)
                .set(ruleArrangeStrategy).equalToWhenPresent(record::getRuleArrangeStrategy)
                .set(ruleIds).equalToWhenPresent(record::getRuleIds)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(latest).equalToWhenPresent(record::getLatest)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(yn).equalToWhenPresent(record::getYn)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(tenant).equalToWhenPresent(record::getTenant)
                .set(packParams).equalToWhenPresent(record::getPackParams)
                .set(ruleContent).equalToWhenPresent(record::getRuleContent);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int updateByPrimaryKey(RulePackDO record) {
        return update(c ->
                c.set(rulePackCode).equalTo(record::getRulePackCode)
                        .set(rulePackName).equalTo(record::getRulePackName)
                        .set(rulePackType).equalTo(record::getRulePackType)
                        .set(ruleArrangeStrategy).equalTo(record::getRuleArrangeStrategy)
                        .set(ruleIds).equalTo(record::getRuleIds)
                        .set(version).equalTo(record::getVersion)
                        .set(latest).equalTo(record::getLatest)
                        .set(remark).equalTo(record::getRemark)
                        .set(status).equalTo(record::getStatus)
                        .set(yn).equalTo(record::getYn)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(modifyTime).equalTo(record::getModifyTime)
                        .set(creator).equalTo(record::getCreator)
                        .set(modifier).equalTo(record::getModifier)
                        .set(tenant).equalTo(record::getTenant)
                        .set(packParams).equalTo(record::getPackParams)
                        .set(ruleContent).equalTo(record::getRuleContent)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_pack")
    default int updateByPrimaryKeySelective(RulePackDO record) {
        return update(c ->
                c.set(rulePackCode).equalToWhenPresent(record::getRulePackCode)
                        .set(rulePackName).equalToWhenPresent(record::getRulePackName)
                        .set(rulePackType).equalToWhenPresent(record::getRulePackType)
                        .set(ruleArrangeStrategy).equalToWhenPresent(record::getRuleArrangeStrategy)
                        .set(ruleIds).equalToWhenPresent(record::getRuleIds)
                        .set(version).equalToWhenPresent(record::getVersion)
                        .set(latest).equalToWhenPresent(record::getLatest)
                        .set(remark).equalToWhenPresent(record::getRemark)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(yn).equalToWhenPresent(record::getYn)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(modifyTime).equalToWhenPresent(record::getModifyTime)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(tenant).equalToWhenPresent(record::getTenant)
                        .set(packParams).equalToWhenPresent(record::getPackParams)
                        .set(ruleContent).equalToWhenPresent(record::getRuleContent)
                        .where(id, isEqualTo(record::getId))
        );
    }
}