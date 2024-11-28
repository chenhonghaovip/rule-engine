package com.jd.cho.rule.engine.infra.convert;

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
public interface RulePackDOConvert {
    RulePackDOConvert INSTANCE = Mappers.getMapper(RulePackDOConvert.class);

    RulePackDO doToDO(RulePackDTO rulePackDTO);

    @Mapping(target = "rulePackType", expression = "java(com.jd.cho.rule.engine.common.enums.RulePackTypeEnum.getByCode(rulePackDO.getRulePackType()))")
    RulePack doToEntity(RulePackDO rulePackDO);
}
