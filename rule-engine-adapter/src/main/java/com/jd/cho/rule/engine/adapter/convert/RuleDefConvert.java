package com.jd.cho.rule.engine.adapter.convert;

import com.jd.cho.rule.engine.dal.DO.RuleDefDO;
import com.jd.cho.rule.engine.domain.model.RuleDef;
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
    @Mapping(target = "ruleActions", expression = "java(com.alibaba.fastjson.JSON.parseArray(ruleDefDO.getRuleAction(), com.jd.cho.rule.engine.domain.model.RuleAction.class))")
    RuleDef doToEntity(RuleDefDO ruleDefDO);

}
