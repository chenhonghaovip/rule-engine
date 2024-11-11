package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.controller.VO.req.RulePackReq;
import com.jd.cho.rule.engine.dal.DO.RulePackDO;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RulePackConvert {
    RulePackConvert INSTANCE = Mappers.getMapper(RulePackConvert.class);

    RulePack doToEntity(RulePackDTO rulePackDTO);

    RulePackDO doToDO(RulePackDTO rulePackDTO);

    @Mapping(target = "rulePackType", expression = "java(com.jd.cho.rule.engine.common.enums.RulePackTypeEnum.getByCode(rulePackReq.getRulePackType()))")
    RulePackDTO doToDTO(RulePackReq rulePackReq);

    RulePack doToEntity(RulePackDO rulePackDO);

}
