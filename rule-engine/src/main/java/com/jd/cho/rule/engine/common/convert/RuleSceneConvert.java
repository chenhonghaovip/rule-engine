package com.jd.cho.rule.engine.common.convert;

import com.jd.cho.rule.engine.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhonghao12
 */
@Mapper(componentModel = "spring")
public interface RuleSceneConvert {
    RuleSceneConvert INSTANCE = Mappers.getMapper(RuleSceneConvert.class);

    RuleScene doToEntity(RuleSceneDO ruleSceneDO);


    RuleSceneDO doToDO(RuleScene ruleScene);

}
