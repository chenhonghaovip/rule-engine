package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleDefDO;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper
public interface RuleDefConvert {
    RuleDefConvert INSTANCE = Mappers.getMapper(RuleDefConvert.class);

    @BeanMapping
    RuleDef doToEntity(RuleDefDO ruleDefDO);


}
