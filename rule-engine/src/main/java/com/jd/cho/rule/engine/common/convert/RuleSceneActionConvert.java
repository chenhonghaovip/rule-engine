package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleSceneActionDO;
import com.jd.cho.rule.engine.domain.model.RuleSceneAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneActionConvert {
    RuleSceneActionConvert INSTANCE = Mappers.getMapper(RuleSceneActionConvert.class);

    @Mapping(target = "actionType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleSceneActionDO.getActionType())")
    RuleSceneAction doToEntity(RuleSceneActionDO ruleSceneActionDO);


}
