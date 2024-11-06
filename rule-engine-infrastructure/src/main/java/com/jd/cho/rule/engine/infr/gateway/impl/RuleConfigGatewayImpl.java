package com.jd.cho.rule.engine.infr.gateway.impl;

import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleSceneDynamicSqlSupport;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleSceneMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleConfigGatewayImpl implements RuleConfigGateway {

    @Resource
    private RuleSceneMapper ruleSceneMapper;

    @Override
    public List<RuleFactor> queryBySceneCode(String sceneCode) {
        Optional<RuleSceneDO> ruleSceneDO = ruleSceneMapper.selectOne(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(sceneCode))
                .and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));

        return null;
    }

    @Override
    public List<RuleDef> ruleDefQuery(List<String> ruleCodes) {
        return null;
    }
}
