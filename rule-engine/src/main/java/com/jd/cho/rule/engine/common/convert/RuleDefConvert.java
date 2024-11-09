package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleDefDO;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.service.dto.RuleDefDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleDefConvert {
    RuleDefConvert INSTANCE = Mappers.getMapper(RuleDefConvert.class);

    @Mapping(target = "ruleCondition", expression = "java(com.alibaba.fastjson.JSON.parseObject(ruleDefDO.getRuleCondition(), com.jd.cho.rule.engine.domain.model.RuleCondition.class))")
    @Mapping(target = "ruleActions", ignore = true)
    RuleDef doToEntity(RuleDefDO ruleDefDO);


    @Mapping(target = "ruleCondition", ignore = true)
    @Mapping(target = "ruleAction", ignore = true)
    RuleDefDO doToEntity(RuleDefDTO ruleDefDTO);

}
