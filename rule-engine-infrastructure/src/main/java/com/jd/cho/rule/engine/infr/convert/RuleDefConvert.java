package com.jd.cho.rule.engine.infr.convert;

import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleDefDO;
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
