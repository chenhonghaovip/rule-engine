package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.service.dto.RuleFactorDTO;
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
    @Mapping(target = "factorType", expression = "java(com.jd.cho.rule.engine.common.enums.FactorTypeEnum.getByCode(ruleFactorDO.getFactorType()))")
    @Mapping(target = "constantType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleFactorDO.getConstantType()))")
    RuleFactor doToEntity(RuleFactorDO ruleFactorDO);


    RuleFactorDTO doToDTO(RuleFactor ruleFactor);


    @Mapping(source = "factorCode", target = "originalFactorCode")
    @Mapping(target = "factorType", expression = "java(com.jd.cho.rule.engine.common.enums.FactorTypeEnum.getByCode(ruleFactorReq.getFactorType()))")
    @Mapping(target = "constantType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleFactorReq.getConstantType()))")
    RuleFactorDTO doToDTO(RuleFactorReq ruleFactorReq);


    RuleFactor doToEntity(RuleFactorDTO ruleFactorDTO);

    @Mapping(target = "factorType", expression = "java(ruleFactor.getFactorType().getCode())")
    @Mapping(target = "constantType", expression = "java(ruleFactor.getConstantType().getCode())")
    RuleFactorDO doToDO(RuleFactor ruleFactor);

}
