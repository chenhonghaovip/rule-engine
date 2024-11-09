package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RulePackDO;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RulePackConvert {
    RulePackConvert INSTANCE = Mappers.getMapper(RulePackConvert.class);

    RulePack doToEntity(RulePackDTO rulePackDTO);

    RulePackDO doToDO(RulePackDTO rulePackDTO);

    RulePack doToEntity(RulePackDO rulePackDO);

}
