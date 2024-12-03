package com.jd.cho.rule.engine.adapter.convert;

import com.jd.cho.rule.engine.adapter.dto.RuleFactorDTO;
import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleFactorConvert {
    RuleFactorConvert INSTANCE = Mappers.getMapper(RuleFactorConvert.class);


    RuleFactorDTO doToDTO(RuleFactor ruleFactor);

    /**
     * !不要直接使用该方法
     *
     * @param ruleFactorReq
     * @return
     * @see AdapterConverters#doToDTO(RuleFactorReq)
     */
    @Mapping(source = "factorCode", target = "originalFactorCode")
    @Mapping(target = "factorType", ignore = true)
    @Mapping(target = "constantType", expression = "java(com.jd.cho.rule.engine.common.enums.ConstantEnum.getByCode(ruleFactorReq.getConstantType()))")
    RuleFactorDTO doToDTO(RuleFactorReq ruleFactorReq);

    RuleFactor doToEntity(RuleFactorDTO ruleFactorDTO);
}
