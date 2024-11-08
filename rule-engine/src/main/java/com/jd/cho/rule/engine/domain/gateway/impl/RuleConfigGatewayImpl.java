package com.jd.cho.rule.engine.domain.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.convert.RuleDefConvert;
import com.jd.cho.rule.engine.common.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.common.convert.RulePackConvert;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.dal.DO.*;
import com.jd.cho.rule.engine.dal.mapper.*;
import com.jd.cho.rule.engine.domain.atomic.AtomicLoginUserComponent;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.*;
import com.jd.cho.rule.engine.service.dto.RuleDefDTO;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import com.jd.cho.rule.engine.spi.RuleFactorExtendService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private RulePackMapper rulePackMapper;

    @Resource
    private RuleSceneMapper ruleSceneMapper;

    @Resource
    private RuleFactorMapper ruleFactorMapper;

    @Resource
    private RuleFactorGroupMapper ruleFactorGroupMapper;

    @Resource
    private AtomicLoginUserComponent atomicLoginUserComponent;

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
    public RulePack rulePackInfo(String rulePackCode) {
        Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectOne(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackCode))
                .and(RulePackDynamicSqlSupport.yn, isEqualTo(true))
                .and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
        if (optionalRulePackDO.isPresent()) {
            RulePackDO rulePackDO = optionalRulePackDO.get();
            List<Long> ids = Arrays.stream(rulePackDO.getRuleIds().split(",")).map(Long::valueOf).collect(Collectors.toList());
            List<RuleDefDO> ruleDefs = ruleDefMapper.select(s -> s.where(RuleDefDynamicSqlSupport.id, isIn(ids)));
            List<RuleDef> rules = ruleDefs.stream().map(each -> {
                RuleDef rulesBean = new RuleDef();
                rulesBean.setRuleCondition(JSON.parseObject(each.getRuleCondition(), RuleCondition.class));
                rulesBean.setRuleActions(JSON.parseArray(each.getRuleAction(), RuleAction.class));
                rulesBean.setPriority(each.getPriority());
                return rulesBean;
            }).collect(Collectors.toList());

            RulePack rulePack = RulePackConvert.INSTANCE.doToEntity(rulePackDO);
            rulePack.setRulePackType(RulePackTypeEnum.getByCode(rulePackDO.getRulePackType()));
            rulePack.setRules(rules);
            return rulePack;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRule(RulePackDTO rulePackDTO) {
        if (Objects.nonNull(rulePackDTO)) {
            String loginUser = atomicLoginUserComponent.getLoginUser();
            String rulePackCode = StringUtils.isNotBlank(rulePackDTO.getRulePackCode()) ? rulePackDTO.getRulePackCode() : UUID.randomUUID().toString();
            rulePackDTO.setRulePackCode(rulePackCode);

            List<RuleDefDO> ruleDefs = rulePackDTO.getRules().stream().map(each -> new RuleDefDO().withRuleAction(JSON.toJSONString(each.getRuleAction())).withRuleAction(JSON.toJSONString(each.getRuleCondition())).withPriority(each.getPriority())).collect(Collectors.toList());
            ruleDefMapper.insertMultiple(ruleDefs);

            String ruleIds = ruleDefs.stream().map(each -> String.valueOf(each.getId())).collect(Collectors.joining(","));
            RulePackDO rulePackDO = RulePackConvert.INSTANCE.doToDO(rulePackDTO);
            rulePackDO.setId(null);
            rulePackDO.setCreator(loginUser);
            rulePackDO.setRuleIds(ruleIds);
            rulePackMapper.insert(rulePackDO);
            return rulePackCode;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateRule(RulePackDTO rulePackDTO) {
        String loginUser = atomicLoginUserComponent.getLoginUser();
        if (Objects.nonNull(rulePackDTO)) {
            // 修改旧版本信息
            Long id = rulePackDTO.getId();
            Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectByPrimaryKey(id);
            optionalRulePackDO.ifPresent(each -> {

                rulePackMapper.update(s -> s.set(RulePackDynamicSqlSupport.modifyTime).equalTo(new Date())
                        .set(RulePackDynamicSqlSupport.modifier).equalTo(loginUser)
                        .set(RulePackDynamicSqlSupport.latest).equalTo(0).where(RulePackDynamicSqlSupport.id, isEqualTo(id)));

                int version = optionalRulePackDO.get().getVersion() + 1;
                this.createRule(rulePackDTO);
            });


        }


    }


    public void batchCreateRule(List<RuleDefDTO> list) {
        String loginUser = atomicLoginUserComponent.getLoginUser();
        List<RuleDefDO> collect = list.stream().map(each -> {
            String ruleCode = StringUtils.isNotBlank(each.getRuleCode()) ? each.getRuleCode() : UUID.randomUUID().toString();
            each.setRuleCode(ruleCode);
            RuleDefDO ruleDefDO = RuleDefConvert.INSTANCE.doToEntity(each);
            ruleDefDO.setCreator(loginUser);
            return ruleDefDO;
        }).collect(Collectors.toList());
        ruleDefMapper.insertMultiple(collect);
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
