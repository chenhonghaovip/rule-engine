package com.jd.cho.rule.engine.infra.convert;

import com.jd.cho.rule.engine.dal.DO.RuleFactorGroupDO;
import com.jd.cho.rule.engine.domain.model.RuleFactorGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleFactorGroupDOConvert {
    RuleFactorGroupDOConvert INSTANCE = Mappers.getMapper(RuleFactorGroupDOConvert.class);

    RuleFactorGroup doToEntity(RuleFactorGroupDO ruleFactorGroupDO);


}
