package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RulePackDO;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper
public interface RulePackConvert {
    RulePackConvert INSTANCE = Mappers.getMapper(RulePackConvert.class);

    @BeanMapping
    RulePack doToEntity(RulePackDTO rulePackDTO);


    @BeanMapping
    RulePackDO doToDO(RulePackDTO rulePackDTO);


    @BeanMapping
    RulePack doToEntity(RulePackDO rulePackDO);

}
