package com.jd.cho.rule.engine.dal.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.annotation.Generated;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table rule_scene
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RuleSceneDO extends BaseEntity {
    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.id")
    private Long id;

    /**
     * Database Column Remarks:
     * 场景编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_code")
    private String sceneCode;

    /**
     * Database Column Remarks:
     * 场景名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_name")
    private String sceneName;

    /**
     * Database Column Remarks:
     * 场景描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.scene_desc")
    private String sceneDesc;

    /**
     * Database Column Remarks:
     * 分组编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.group_code")
    private String groupCode;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_scene.yn")
    private Boolean yn;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withId(Long id) {
        this.setId(id);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withSceneCode(String sceneCode) {
        this.setSceneCode(sceneCode);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withSceneName(String sceneName) {
        this.setSceneName(sceneName);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withSceneDesc(String sceneDesc) {
        this.setSceneDesc(sceneDesc);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withGroupCode(String groupCode) {
        this.setGroupCode(groupCode);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_scene")
    public RuleSceneDO withYn(Boolean yn) {
        this.setYn(yn);
        return this;
    }
}