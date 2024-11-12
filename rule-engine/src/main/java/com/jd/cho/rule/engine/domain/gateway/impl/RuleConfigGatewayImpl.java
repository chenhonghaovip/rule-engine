package com.jd.cho.rule.engine.domain.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.convert.*;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
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
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
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
    private RuleSceneActionMapper ruleSceneActionMapper;

    @Resource
    private RuleFactorGroupMapper ruleFactorGroupMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRuleScene(RuleScene ruleScene) {
        UserInfo userInfo = AtomicLoginUserComponent.getLoginUserInfo();
        RuleSceneDO ruleSceneDO = RuleSceneConvert.INSTANCE.doToDO(ruleScene);
        String groupCode = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.joining(Dict.SPLIT));
        ruleSceneDO.setGroupCode(groupCode);
        ruleSceneDO.setCreator(userInfo.getLoginUser());
        ruleSceneDO.setTenant(userInfo.getTenant());
        ruleSceneMapper.insertSelective(ruleSceneDO);

        if (CollectionUtils.isNotEmpty(ruleScene.getRuleSceneActions())) {
            this.createRuleSceneAction(ruleScene.getRuleSceneActions(), ruleScene.getSceneCode());
        }
        return ruleScene.getSceneCode();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RuleScene> queryRuleScene() {
        String tenant = AtomicLoginUserComponent.getLoginUserInfo().getTenant();
        List<RuleSceneDO> ruleSceneList = ruleSceneMapper.select(s -> s.where(RuleSceneDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleSceneDynamicSqlSupport.tenant, isEqualTo(tenant)));

        List<String> groupCodes = Lists.newArrayList();
        List<String> sceneCodes = Lists.newArrayList();
        ruleSceneList.forEach(each -> {
            groupCodes.addAll(Arrays.stream(each.getGroupCode().split(Dict.SPLIT)).collect(Collectors.toList()))
            sceneCodes.add(each.getSceneCode());
        });

        List<RuleFactorGroup> ruleFactorGroups = this.queryRuleFactorGroup(groupCodes);
        Map<String, RuleFactorGroup> groupMaps = ruleFactorGroups.stream().collect(Collectors.toMap(RuleFactorGroup::getGroupCode, Function.identity()));

        List<RuleSceneAction> ruleSceneActions = this.queryRuleSceneAction(sceneCodes);
        Map<String, List<RuleSceneAction>> sceneActionMap = ruleSceneActions.stream().collect(Collectors.groupingBy(RuleSceneAction::getSceneCode));

        return ruleSceneList.stream().map(each -> {
            RuleScene ruleScene = RuleSceneConvert.INSTANCE.doToEntity(each);
            List<RuleFactorGroup> groups = Arrays.stream(each.getGroupCode().split(Dict.SPLIT)).map(groupMaps::get).collect(Collectors.toList());
            ruleScene.setRuleSceneActions(sceneActionMap.get(each.getSceneCode()));
            ruleScene.setRuleFactorGroups(groups);
            return ruleScene;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRuleScene(RuleScene ruleScene) {
        ruleScene.setSceneCode(null);
        RuleSceneDO ruleSceneDO = RuleSceneConvert.INSTANCE.doToDO(ruleScene);
        String groupCode = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.joining(Dict.SPLIT));
        ruleSceneDO.setGroupCode(groupCode);
        ruleSceneDO.setModifier(AtomicLoginUserComponent.getLoginUser());
        ruleSceneDO.setModifyTime(new Date());
        ruleSceneMapper.updateByPrimaryKey(ruleSceneDO);

        if (CollectionUtils.isNotEmpty(ruleScene.getRuleSceneActions())) {
            // TODO 需要重新获取场景code
            this.updateRuleSceneAction(ruleScene.getRuleSceneActions(), ruleScene.getSceneCode());
        }


    }

    @Override
    public List<RuleSceneAction> queryRuleSceneAction(List<String> sceneCodes) {
        List<RuleSceneActionDO> ruleSceneActionDOList = ruleSceneActionMapper.select(s -> s.where(RuleSceneActionDynamicSqlSupport.sceneCode, isIn(sceneCodes))
                .and(RuleSceneActionDynamicSqlSupport.yn, isEqualTo(true)));
        return ruleSceneActionDOList.stream().map(RuleSceneActionConvert.INSTANCE::doToEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode) {
        if (CollectionUtils.isNotEmpty(ruleSceneActions)) {
            UserInfo userInfo = AtomicLoginUserComponent.getLoginUserInfo();
            List<RuleSceneActionDO> collect = ruleSceneActions.stream().map(each -> RuleSceneActionDO.builder().actionCode(each.getActionCode())
                    .actionType(each.getActionType().getCode()).action(each.getAction()).sceneCode(sceneCode)
                    .creator(userInfo.getLoginUser()).tenant(userInfo.getTenant()).build()).collect(Collectors.toList());
            ruleSceneActionMapper.insertMultiple(collect);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode) {
        ruleSceneActionMapper.update(s -> s.set(RuleSceneActionDynamicSqlSupport.yn).equalTo(false)
                .set(RuleSceneActionDynamicSqlSupport.modifier).equalTo(AtomicLoginUserComponent.getLoginUser())
                .set(RuleSceneActionDynamicSqlSupport.modifyTime).equalTo(new Date())
                .where(RuleSceneActionDynamicSqlSupport.sceneCode, isEqualTo(sceneCode))
                .and(RuleSceneActionDynamicSqlSupport.yn, isEqualTo(true)));
        if (CollectionUtils.isNotEmpty(ruleSceneActions)) {
            this.createRuleSceneAction(ruleSceneActions, sceneCode);
        }
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroup() {
        UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
        List<RuleFactorGroupDO> list = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleFactorGroupDynamicSqlSupport.tenant, isEqualTo(loginUserInfo.getTenant())));
        return list.stream().map(each -> RuleFactorGroup.builder().groupCode(each.getGroupCode()).groupName(each.getGroupName()).id(each.getId()).build()).collect(Collectors.toList());
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroup(List<String> groupCodes) {
        UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
        List<RuleFactorGroupDO> list = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleFactorGroupDynamicSqlSupport.tenant, isEqualTo(loginUserInfo.getTenant())).and(RuleFactorGroupDynamicSqlSupport.groupCode, isIn(groupCodes)));
        return list.stream().map(each -> RuleFactorGroup.builder().groupCode(each.getGroupCode()).groupName(each.getGroupName()).id(each.getId()).build()).collect(Collectors.toList());
    }

    @Override
    public String createRuleFactorGroup(RuleFactorGroup ruleFactorGroup) {
        Assert.notNull(ruleFactorGroup, "数据不能为空");
        if (StringUtils.isBlank(ruleFactorGroup.getGroupCode())) {
            ruleFactorGroup.setGroupCode(UUID.randomUUID().toString());
        }
        UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
        RuleFactorGroupDO ruleFactorGroupDO = RuleFactorGroupDO.builder().groupCode(ruleFactorGroup.getGroupCode())
                .groupName(ruleFactorGroup.getGroupName()).creator(loginUserInfo.getLoginUser())
                .tenant(loginUserInfo.getTenant()).build();
        ruleFactorGroupMapper.insertSelective(ruleFactorGroupDO);
        return ruleFactorGroup.getGroupCode();
    }

    @Override
    public void updateRuleFactorGroup(RuleFactorGroup ruleFactorGroup) {
        Assert.notNull(ruleFactorGroup, "数据不能为空");
        UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
        RuleFactorGroupDO ruleFactorGroupDO = RuleFactorGroupDO.builder().groupCode(ruleFactorGroup.getGroupCode())
                .groupName(ruleFactorGroup.getGroupName()).modifier(loginUserInfo.getLoginUser())
                .modifyTime(new Date()).build();
        ruleFactorGroupMapper.updateByPrimaryKey(ruleFactorGroupDO);
    }

    @Override
    public void createRuleFactor(RuleFactor ruleFactor) {
        AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotBlank(ruleFactor.getFactorCode(), BizErrorEnum.DOES_NOT_EXIST);
        UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();

        long count = ruleFactorMapper.count(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(ruleFactor.getFactorCode()))
                .and(RuleFactorDynamicSqlSupport.tenant, isEqualTo(loginUserInfo.getTenant()))
                .and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (count > 0) {
            throw new BusinessException(BizErrorEnum.CODE_IS_EXIST);
        }

        RuleFactorDO ruleFactorDO = RuleFactorConvert.INSTANCE.doToDO(ruleFactor);

        ruleFactorDO.setCreator(loginUserInfo.getLoginUser());
        ruleFactorDO.setTenant(loginUserInfo.getTenant());
        ruleFactorMapper.insertSelective(ruleFactorDO);
    }

    @Override
    public void updateRuleFactor(RuleFactor ruleFactor) {
        AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotBlank(ruleFactor.getFactorCode(), BizErrorEnum.DOES_NOT_EXIST);
        RuleFactorDO ruleFactorDO = RuleFactorConvert.INSTANCE.doToDO(ruleFactor);
        ruleFactorDO.setModifier(AtomicLoginUserComponent.getLoginUserInfo().getLoginUser());
        ruleFactorDO.setModifyTime(new Date());
        ruleFactorDO.setFactorCode(null);
        ruleFactorMapper.updateByPrimaryKeySelective(ruleFactorDO);
    }

    @Override
    public List<RuleFactor> queryBySceneCode(String sceneCode, Map<String, Object> context) {
        Optional<RuleSceneDO> optionalRuleScene = ruleSceneMapper.selectOne(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(sceneCode))
                .and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));
        if (!optionalRuleScene.isPresent()) {
            return Lists.newArrayList();
        }
        RuleSceneDO ruleSceneDO = optionalRuleScene.get();
        List<String> groupCodes = Arrays.stream(ruleSceneDO.getGroupCode().split(Dict.SPLIT)).collect(Collectors.toList());
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
    public List<CommonDict> factorConstantValues(Map<String, Object> context) {
        String factorCode = (String) context.get(Dict.FACTOR_CODE);
        List<RuleFactorDO> ruleFactorDOS = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(factorCode))
                .and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (CollectionUtils.isEmpty(ruleFactorDOS)) {
            throw new BusinessException(BizErrorEnum.DOES_NOT_EXIST);
        }
        RuleFactorDO ruleFactorDO = ruleFactorDOS.get(0);
        return getDict(ruleFactorDO.getConstantType(), ruleFactorDO.getConstantValue(), context);
    }

    @Override
    public RulePack rulePackInfo(String rulePackCode) {
        Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectOne(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackCode))
                .and(RulePackDynamicSqlSupport.yn, isEqualTo(true))
                .and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
        if (optionalRulePackDO.isPresent()) {
            RulePackDO rulePackDO = optionalRulePackDO.get();
            List<Long> ids = Arrays.stream(rulePackDO.getRuleIds().split(Dict.SPLIT)).map(Long::valueOf).collect(Collectors.toList());
            List<RuleDef> ruleDefs = getRulePackInfo(ids);

            RulePack rulePack = RulePackConvert.INSTANCE.doToEntity(rulePackDO);
            rulePack.setRulePackType(RulePackTypeEnum.getByCode(rulePackDO.getRulePackType()));
            rulePack.setRules(ruleDefs);
            return rulePack;
        }
        return null;
    }

    @Override
    public List<RulePack> historyRulePackInfo(String rulePackCode) {
        List<RulePackDO> history = rulePackMapper.select(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackCode))
                .and(RulePackDynamicSqlSupport.yn, isEqualTo(true)).orderBy(RulePackDynamicSqlSupport.version.descending()));

        List<Long> ruleIds = Lists.newArrayList();
        history.forEach(each -> ruleIds.addAll(Arrays.stream(each.getRuleIds().split(Dict.SPLIT)).map(Long::valueOf).collect(Collectors.toList())));
        List<RuleDef> ruleDefs = getRulePackInfo(ruleIds);
        Map<Long, RuleDef> ruleDefMaps = ruleDefs.stream().collect(Collectors.toMap(RuleDef::getId, Function.identity()));

        return history.stream().map(each -> {
            RulePack rulePack = RulePackConvert.INSTANCE.doToEntity(each);
            rulePack.setRulePackType(RulePackTypeEnum.getByCode(each.getRulePackType()));
            List<RuleDef> currentRuleDefs = Arrays.stream(each.getRuleIds().split(Dict.SPLIT)).map(Long::valueOf).map(ruleDefMaps::get).filter(Objects::nonNull).collect(Collectors.toList());
            rulePack.setRules(currentRuleDefs);
            return rulePack;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRulePack(RulePackDTO rulePackDTO) {
        if (Objects.nonNull(rulePackDTO)) {
            long count = rulePackMapper.count(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackDTO.getRulePackCode()))
                    .and(RulePackDynamicSqlSupport.yn, isEqualTo(true)));
            if (count > 0) {
                throw new BusinessException("规则包code重复");
            }
            UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
            String rulePackCode = StringUtils.isNotBlank(rulePackDTO.getRulePackCode()) ? rulePackDTO.getRulePackCode() : UUID.randomUUID().toString();
            rulePackDTO.setRulePackCode(rulePackCode);
            return insertRuleGroup(rulePackDTO, 1, loginUserInfo);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRulePack(RulePackDTO rulePackDTO) {

        if (Objects.nonNull(rulePackDTO)) {
            // 修改旧版本信息
            Long id = rulePackDTO.getId();
            Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectByPrimaryKey(id);
            optionalRulePackDO.ifPresent(each -> {

                UserInfo loginUserInfo = AtomicLoginUserComponent.getLoginUserInfo();
                int update = rulePackMapper.update(s -> s.set(RulePackDynamicSqlSupport.modifyTime).equalTo(new Date())
                        .set(RulePackDynamicSqlSupport.modifier).equalTo(loginUserInfo.getLoginUser())
                        .set(RulePackDynamicSqlSupport.latest).equalTo(0)
                        .where(RulePackDynamicSqlSupport.id, isEqualTo(id)).and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
                if (update == 0) {
                    throw new BusinessException(DATA_HAS_CHANGE);
                }

                rulePackDTO.setRulePackCode(each.getRulePackCode());
                int version = optionalRulePackDO.get().getVersion() + 1;
                insertRuleGroup(rulePackDTO, version, loginUserInfo);
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

    private List<RuleDef> getRulePackInfo(List<Long> ruleIds) {
        List<RuleDefDO> ruleDefs = ruleDefMapper.select(s -> s.where(RuleDefDynamicSqlSupport.id, isIn(ruleIds)));
        return ruleDefs.stream().map(RuleDefConvert.INSTANCE::doToEntity).collect(Collectors.toList());
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

        return ruleFactors.stream().collect(Collectors.toMap(RuleFactorDO::getFactorCode, each -> Arrays.stream(each.getFactorScriptParam().split(Dict.SPLIT)).collect(Collectors.toList())));
    }


    /**
     * 添加规则包信息
     *
     * @param rulePackDTO   规则包
     * @param version       规则包版本
     * @param loginUserInfo 登录人
     * @return 规则包编号
     */
    private String insertRuleGroup(RulePackDTO rulePackDTO, int version, UserInfo loginUserInfo) {
        List<RuleDefDO> ruleDefs = rulePackDTO.getRules().stream()
                .map(o -> RuleDefDO.builder().ruleAction(JSON.toJSONString(o.getRuleActions()))
                        .ruleCondition(JSON.toJSONString(o.getRuleCondition()))
                        .priority(o.getPriority()).creator(loginUserInfo.getLoginUser()).tenant(loginUserInfo.getTenant()).build()).collect(Collectors.toList());
        ruleDefMapper.insertMultipleSelective(ruleDefs);

        List<RuleCondition> ruleConditions = rulePackDTO.getRules().stream().map(each -> JSON.parseObject(JSON.toJSONString(each.getRuleCondition()), RuleCondition.class)).collect(Collectors.toList());
        Map<String, List<String>> factorScriptParam = getFactorScriptParam(ruleConditions);

        String ruleIds = ruleDefs.stream().filter(each -> Objects.nonNull(each.getId())).map(o -> String.valueOf(o.getId())).collect(Collectors.joining(Dict.SPLIT));
        RulePackDO rulePackDO = RulePackConvert.INSTANCE.doToDO(rulePackDTO);
        rulePackDO.setCreator(loginUserInfo.getLoginUser());
        rulePackDO.setRuleIds(ruleIds);
        rulePackDO.setVersion(version);
        rulePackDO.setTenant(loginUserInfo.getTenant());
        rulePackDO.setPackParams(JSON.toJSONString(factorScriptParam));
        rulePackMapper.insertSelective(rulePackDO);
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
