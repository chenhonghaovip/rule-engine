package com.jd.cho.rule.engine.infra.convert;

import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleFactorDOConvert {
    RuleFactorDOConvert INSTANCE = Mappers.getMapper(RuleFactorDOConvert.class);

    /**
     * !不要直接使用该方法
     *
     * @param ruleFactorDO
     * @return
     * @see InfraConverters#doToEntity(RuleFactorDO)
     */
    @Mapping(source = "factorCode", target = "originalFactorCode")
    @Mapping(target = "factorType", ignore = true)
    @Mapping(target = "constantType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleFactorDO.getConstantType()))")
    RuleFactor doToEntity(RuleFactorDO ruleFactorDO);

    @Mapping(target = "factorType", expression = "java(ruleFactor.getFactorType().getCode())")
    @Mapping(target = "constantType", expression = "java(ruleFactor.getConstantType().getCode())")
    RuleFactorDO doToDO(RuleFactor ruleFactor);
}
