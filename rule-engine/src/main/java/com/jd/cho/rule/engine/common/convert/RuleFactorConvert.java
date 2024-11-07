package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.common.dto.RuleFactorQueryDTO;
import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper
public interface RuleFactorConvert {
    RuleFactorConvert INSTANCE = Mappers.getMapper(RuleFactorConvert.class);

    @BeanMapping
    RuleFactor doToEntity(RuleFactorDO ruleFactorDO);

    @BeanMapping
    RuleFactorQueryDTO.RuleFactorBean doToDTO(RuleFactor ruleFactor);

}
