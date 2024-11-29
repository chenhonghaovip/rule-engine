package com.jd.cho.rule.engine.core.convert;

import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author liweichao11
 * @date 2024/11/28
 */
@Mapper(componentModel = "spring")
public interface RuleFactorConvert {
    RuleFactorConvert INSTANCE = Mappers.getMapper(RuleFactorConvert.class);

    RuleFactor doToEntity(RuleFactor ruleFactor);
}
