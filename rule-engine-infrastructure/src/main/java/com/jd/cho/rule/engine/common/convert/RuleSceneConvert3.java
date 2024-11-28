package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import com.jd.cho.rule.engine.service.dto.RuleSceneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneConvert3 {
    RuleSceneConvert3 INSTANCE = Mappers.getMapper(RuleSceneConvert3.class);

    RuleScene doToEntity(RuleSceneDO ruleSceneDO);

    RuleScene doToEntity(RuleSceneDTO ruleSceneDTO);

    @Mapping(target = "groupCode", expression = "java(ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(java.util.stream.Collectors.joining(com.jd.cho.rule.engine.common.dict.Dict.SPLIT)))")
    RuleSceneDO doToDO(RuleScene ruleScene);
}
