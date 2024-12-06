package com.jd.cho.rule.engine.adapter.convert;

import com.jd.cho.rule.engine.adapter.dto.RuleActionDTO;
import com.jd.cho.rule.engine.adapter.dto.RuleSceneDTO;
import com.jd.cho.rule.engine.controller.VO.req.RuleActionReq;
import com.jd.cho.rule.engine.controller.VO.req.RuleSceneReq;
import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneConvert {
    RuleSceneConvert INSTANCE = Mappers.getMapper(RuleSceneConvert.class);

    RuleScene doToEntity(RuleSceneDO ruleSceneDO);


    RuleScene doToEntity(RuleSceneDTO ruleSceneDTO);


    @Mapping(target = "groupCode", expression = "java(ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(java.util.stream.Collectors.joining(com.jd.cho.rule.engine.common.dict.Dict.SPLIT)))")
    RuleSceneDO doToDO(RuleScene ruleScene);


    RuleSceneResp doToResp(RuleScene ruleScene);


    RuleSceneDTO doToDTO(RuleSceneReq ruleSceneReq);

    @Mapping(target = "actionType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleActionReq.getActionType()))")
    RuleActionDTO doToDTO(RuleActionReq ruleActionReq);

}
