package com.jd.cho.rule.engine.infra.convert;

import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneDOConvert {
    RuleSceneDOConvert INSTANCE = Mappers.getMapper(RuleSceneDOConvert.class);

    RuleScene doToEntity(RuleSceneDO ruleSceneDO);

    @Mapping(target = "groupCode", expression = "java(ruleScene.getRuleFactorGroups().stream().map(com.jd.cho.rule.engine.domain.model.RuleFactorGroup::getGroupCode).collect(java.util.stream.Collectors.joining(com.jd.cho.rule.engine.common.dict.Dict.SPLIT)))")
    RuleSceneDO doToDO(RuleScene ruleScene);
}
