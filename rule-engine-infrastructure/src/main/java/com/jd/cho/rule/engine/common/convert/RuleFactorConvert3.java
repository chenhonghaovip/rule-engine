package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleFactorConvert3 {
    RuleFactorConvert3 INSTANCE = Mappers.getMapper(RuleFactorConvert3.class);

    @Mapping(source = "factorCode", target = "originalFactorCode")
    @Mapping(target = "factorType", expression = "java(com.jd.cho.rule.engine.common.enums.FactorTypeEnum.getByCode(ruleFactorDO.getFactorType()))")
    @Mapping(target = "constantType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleFactorDO.getConstantType()))")
    RuleFactor doToEntity(RuleFactorDO ruleFactorDO);

    @Mapping(target = "factorType", expression = "java(ruleFactor.getFactorType().getCode())")
    @Mapping(target = "constantType", expression = "java(ruleFactor.getConstantType().getCode())")
    RuleFactorDO doToDO(RuleFactor ruleFactor);

}
