package com.jd.cho.rule.engine.infra.convert;

import com.jd.cho.rule.engine.dal.DO.RuleSceneActionDO;
import com.jd.cho.rule.engine.domain.model.RuleSceneAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneActionDOConvert {
    RuleSceneActionDOConvert INSTANCE = Mappers.getMapper(RuleSceneActionDOConvert.class);

    @Mapping(target = "actionType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleSceneActionDO.getActionType()))")
    RuleSceneAction doToEntity(RuleSceneActionDO ruleSceneActionDO);

    @Mapping(target = "actionType", expression = "java(ruleSceneAction.getActionType().getCode())")
    RuleSceneActionDO doToDO(RuleSceneAction ruleSceneAction);

}
