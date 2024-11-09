package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleFactorConvert {
    RuleFactorConvert INSTANCE = Mappers.getMapper(RuleFactorConvert.class);

    @Mapping(source = "factorCode", target = "originalFactorCode")
    RuleFactor doToEntity(RuleFactorDO ruleFactorDO);


    RuleFactorQueryDTO.RuleFactorBean doToDTO(RuleFactor ruleFactor);

}
