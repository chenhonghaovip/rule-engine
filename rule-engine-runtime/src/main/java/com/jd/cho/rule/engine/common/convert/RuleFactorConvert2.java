package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author liweichao11
 * @date 2024/11/28
 */
@Mapper(componentModel = "spring")
public interface RuleFactorConvert2 {
    RuleFactorConvert2 INSTANCE = Mappers.getMapper(RuleFactorConvert2.class);



    RuleFactor doToEntity(RuleFactor ruleFactor);
}
