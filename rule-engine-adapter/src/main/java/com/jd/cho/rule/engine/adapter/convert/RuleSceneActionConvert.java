package com.jd.cho.rule.engine.adapter.convert;

import com.jd.cho.rule.engine.controller.VO.req.RuleActionReq;
import com.jd.cho.rule.engine.dal.DO.RuleSceneActionDO;
import com.jd.cho.rule.engine.domain.model.RuleSceneAction;
import com.jd.cho.rule.engine.adapter.dto.RuleActionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneActionConvert {
    RuleSceneActionConvert INSTANCE = Mappers.getMapper(RuleSceneActionConvert.class);

    @Mapping(target = "actionType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleSceneActionDO.getActionType()))")
    RuleSceneAction doToEntity(RuleSceneActionDO ruleSceneActionDO);


    @Mapping(target = "actionType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleActionReq.getActionType()))")
    RuleActionDTO doToDTO(RuleActionReq ruleActionReq);


    @Mapping(target = "actionType", expression = "java(ruleSceneAction.getActionType().getCode())")
    RuleSceneActionDO doToDO(RuleSceneAction ruleSceneAction);

}
