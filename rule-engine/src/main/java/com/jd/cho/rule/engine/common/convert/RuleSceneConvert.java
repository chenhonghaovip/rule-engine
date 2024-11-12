package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.controller.VO.req.RuleSceneReq;
import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import com.jd.cho.rule.engine.service.dto.RuleSceneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneConvert {
    RuleSceneConvert INSTANCE = Mappers.getMapper(RuleSceneConvert.class);

    RuleScene doToEntity(RuleSceneDO ruleSceneDO);


    RuleScene doToEntity(RuleSceneDTO ruleSceneDTO);


    RuleSceneDO doToDO(RuleScene ruleScene);


    RuleSceneResp doToResp(RuleScene ruleScene);


    RuleSceneDTO doToDTO(RuleSceneReq ruleSceneReq);


}
