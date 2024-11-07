package com.jd.cho.rule.engine.infr.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.client.dto.RuleDefDTO;
import com.jd.cho.rule.engine.common.client.dto.RuleDefQueryDTO;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.gateway.extend.RuleFactorExtendService;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.infr.common.QlExpressUtil;
import com.jd.cho.rule.engine.infr.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleDefDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleFactorGroupDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleSceneDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleConfigGatewayImpl implements RuleConfigGateway {

    @Resource
    private RuleDefMapper ruleDefMapper;

    @Resource
    private RuleSceneMapper ruleSceneMapper;

    @Resource
    private RuleFactorMapper ruleFactorMapper;

    @Resource
    private RuleFactorGroupMapper ruleFactorGroupMapper;

    @Override
    public List<RuleFactor> queryBySceneCode(String sceneCode) {
        Optional<RuleSceneDO> optionalRuleScene = ruleSceneMapper.selectOne(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(sceneCode))
                .and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));
        if (!optionalRuleScene.isPresent()) {
            return Lists.newArrayList();
        }
        RuleSceneDO ruleSceneDO = optionalRuleScene.get();
        List<String> groupCodes = Arrays.stream(ruleSceneDO.getGroupCode().split(",")).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(groupCodes)) {
            return Lists.newArrayList();
        }

        List<RuleFactorGroupDO> groupList = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.groupCode, isIn(groupCodes)));
        Map<String, String> groupMaps = groupList.stream().collect(Collectors.toMap(RuleFactorGroupDO::getGroupCode, RuleFactorGroupDO::getGroupName));
        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.groupCode, isIn(groupCodes)).and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));

        List<RuleFactor> ruleFactorList = ruleFactors.stream().map(each -> {
            RuleFactor ruleFactor = RuleFactorConvert.INSTANCE.doToEntity(each);
            ruleFactor.setGroupName(groupMaps.get(each.getGroupCode()));
            ruleFactor.setConstantValues(getDict(each.getConstantType(), each.getConstantValue()));
            ruleFactor.setExpressOperationList(ExpressOperationEnum.MAP.get(each.getFactorType()));
            return ruleFactor;
        }).collect(Collectors.toList());

        ServiceLoader<RuleFactorExtendService> load = ServiceLoader.load(RuleFactorExtendService.class);
        for (RuleFactorExtendService service : load) {
            ruleFactorList = service.extendFactors(ruleFactorList);
        }
        return ruleFactorList;
    }

    @Override
    public List<RuleDef> ruleDefQuery(List<String> ruleCodes) {
        List<RuleDefDO> ruleDefs = ruleDefMapper.select(s -> s.where(RuleDefDynamicSqlSupport.ruleCode, isIn(ruleCodes))
                .and(RuleDefDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleDefDynamicSqlSupport.latest, isEqualTo(1))
                .orderBy(RuleDefDynamicSqlSupport.priority.descending())
        );
        return null;
    }

    @Override
    public List<RuleDefDTO> batchCreateRule(List<RuleDefDTO> list) {
        return null;
    }

    @Override
    public void batchUpdateRule(List<RuleDefDTO> list) {

    }

    @Override
    public List<RuleDefQueryDTO> queryByRuleCodes(List<String> ruleCodes) {
        return null;
    }

    @Override
    public List<RuleDefQueryDTO> queryByRuleCodes(String ruleCode) {
        return null;
    }

    @Override
    public List<String> queryParams(List<String> ruleCodes) {
        return null;
    }


    private List<CommonDict> getDict(String constantType, String constantValue) {
        if (ConstantEnum.INPUT.getCode().equals(constantType)) {
            return Lists.newArrayList();
        } else if (ConstantEnum.SCRIPT.getCode().equals(constantType)) {
            constantValue = JSON.toJSONString(QlExpressUtil.execute(constantValue, Maps.newHashMap()));
        }
        return JSON.parseArray(constantValue, CommonDict.class);
    }


}
