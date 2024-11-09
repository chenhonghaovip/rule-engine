package com.jd.cho.rule.engine.domain.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.common.convert.RulePackConvert;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.dal.DO.*;
import com.jd.cho.rule.engine.dal.mapper.*;
import com.jd.cho.rule.engine.domain.atomic.AtomicLoginUserComponent;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.*;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import com.jd.cho.rule.engine.spi.RuleFactorExtendService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static com.jd.cho.rule.engine.common.exceptions.BizErrorEnum.DATA_HAS_CHANGE;
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
    public List<RuleFactor> queryBySceneCode(String sceneCode, Map<String, Object> context) {
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
            ruleFactor.setConstantValues(getDict(each.getConstantType(), each.getConstantValue(), context));
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
    public String createRulePack(RulePackDTO rulePackDTO) {
        if (Objects.nonNull(rulePackDTO)) {
            String loginUser = atomicLoginUserComponent.getLoginUser();
            String rulePackCode = StringUtils.isNotBlank(rulePackDTO.getRulePackCode()) ? rulePackDTO.getRulePackCode() : UUID.randomUUID().toString();
            rulePackDTO.setRulePackCode(rulePackCode);
            return insertRuleGroup(rulePackDTO, 1, loginUser);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRulePack(RulePackDTO rulePackDTO) {
        String loginUser = atomicLoginUserComponent.getLoginUser();
        if (Objects.nonNull(rulePackDTO)) {
            // 修改旧版本信息
            Long id = rulePackDTO.getId();
            Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectByPrimaryKey(id);
            optionalRulePackDO.ifPresent(each -> {

                int update = rulePackMapper.update(s -> s.set(RulePackDynamicSqlSupport.modifyTime).equalTo(new Date())
                        .set(RulePackDynamicSqlSupport.modifier).equalTo(loginUser)
                        .set(RulePackDynamicSqlSupport.latest).equalTo(0)
                        .where(RulePackDynamicSqlSupport.id, isEqualTo(id)).and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
                if (update == 0) {
                    throw new BusinessException(DATA_HAS_CHANGE);
                }

                int version = optionalRulePackDO.get().getVersion() + 1;
                insertRuleGroup(rulePackDTO, version, loginUser);
            });
        }
    }

    @Override
    public Map<String, List<String>> queryFactorParamsWithMap(String rulePackCode) {
        RulePack rulePack = this.rulePackInfo(rulePackCode);
        if (Objects.nonNull(rulePack) && StringUtils.isNotBlank(rulePack.getPackParams())) {
            Type type = new TypeToken<Map<String, List<String>>>() {
            }.getType();
            return new Gson().fromJson(rulePack.getPackParams(), type);
        }
        return Maps.newHashMap();
    }

    @Override
    public Set<String> queryFactorParams(String rulePackCode) {
        Set<String> paramCodes = Sets.newHashSet();
        RulePack rulePack = this.rulePackInfo(rulePackCode);
        if (Objects.nonNull(rulePack) && StringUtils.isNotBlank(rulePack.getPackParams())) {
            Type type = new TypeToken<Map<String, List<String>>>() {
            }.getType();
            Map<String, List<String>> resultMap = new Gson().fromJson(rulePack.getPackParams(), type);
            resultMap.forEach((key, value) -> paramCodes.addAll(value));
        }
        return paramCodes;
    }

    @Override
    public Set<String> queryFactors(String rulePackCode) {
        Set<String> factorCodes = Sets.newHashSet();
        RulePack rulePack = this.rulePackInfo(rulePackCode);
        if (Objects.nonNull(rulePack) && StringUtils.isNotBlank(rulePack.getPackParams())) {
            Type type = new TypeToken<Map<String, List<String>>>() {
            }.getType();
            Map<String, List<String>> resultMap = new Gson().fromJson(rulePack.getPackParams(), type);
            resultMap.forEach((key, value) -> factorCodes.add(key));
        }
        return factorCodes;
    }


    /**
     * 获取当前包中所需变量信息
     *
     * @param rules 变量信息
     * @return key:因子-value:因子入参
     */
    private Map<String, List<String>> getFactorScriptParam(List<RuleCondition> rules) {
        Set<String> factorCodes = Sets.newHashSet();
        rules.forEach(each -> findFactorCodes(each, factorCodes));

        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleFactorDynamicSqlSupport.factorCode, isIn(factorCodes)));

        return ruleFactors.stream().collect(Collectors.toMap(RuleFactorDO::getFactorCode, each -> Arrays.stream(each.getFactorScriptParam().split(",")).collect(Collectors.toList())));
    }


    /**
     * 添加规则包信息
     *
     * @param rulePackDTO 规则包
     * @param version     规则包版本
     * @param loginUser   登录人
     * @return 规则包编号
     */
    private String insertRuleGroup(RulePackDTO rulePackDTO, int version, String loginUser) {
        List<RuleDefDO> ruleDefs = rulePackDTO.getRules().stream().map(o -> new RuleDefDO().withRuleAction(JSON.toJSONString(o.getRuleAction())).withRuleAction(JSON.toJSONString(o.getRuleCondition())).withPriority(o.getPriority())).collect(Collectors.toList());
        ruleDefMapper.insertMultiple(ruleDefs);

        List<RuleCondition> ruleConditions = rulePackDTO.getRules().stream().map(each -> JSON.parseObject(JSON.toJSONString(each.getRuleCondition()), RuleCondition.class)).collect(Collectors.toList());
        Map<String, List<String>> factorScriptParam = getFactorScriptParam(ruleConditions);

        String ruleIds = ruleDefs.stream().map(o -> String.valueOf(o.getId())).collect(Collectors.joining(","));
        RulePackDO rulePackDO = RulePackConvert.INSTANCE.doToDO(rulePackDTO);
        rulePackDO.setCreator(loginUser);
        rulePackDO.setRuleIds(ruleIds);
        rulePackDO.setVersion(version);
        rulePackDO.setPackParams(JSON.toJSONString(factorScriptParam));
        rulePackMapper.insert(rulePackDO);
        return rulePackDTO.getRulePackCode();
    }


    /**
     * 递归获取表达式中的字段
     *
     * @param ruleCondition 规则条件
     * @param resultCodes   结果
     */
    private void findFactorCodes(RuleCondition ruleCondition, Set<String> resultCodes) {
        boolean isExpress = StringUtils.isNotBlank(ruleCondition.getFactorCode())
                && StringUtils.isNotBlank(ruleCondition.getCompareOperation())
                && StringUtils.isBlank(ruleCondition.getLogicOperation());
        if (isExpress) {
            resultCodes.add(ruleCondition.getFactorCode());
        } else if (CollectionUtils.isNotEmpty(ruleCondition.getChildren())) {
            ruleCondition.getChildren().forEach(each -> findFactorCodes(each, resultCodes));
        }
    }

    /**
     * 获取字典
     *
     * @param constantType  常量类型
     * @param constantValue 常量值
     * @return 字典
     */
    private List<CommonDict> getDict(String constantType, String constantValue, Map<String, Object> context) {
        if (ConstantEnum.INPUT.getCode().equals(constantType)) {
            return Lists.newArrayList();
        } else if (ConstantEnum.SCRIPT.getCode().equals(constantType)) {
            constantValue = JSON.toJSONString(QlExpressUtil.execute(constantValue, context));
        }
        return JSON.parseArray(constantValue, CommonDict.class);
    }


}
