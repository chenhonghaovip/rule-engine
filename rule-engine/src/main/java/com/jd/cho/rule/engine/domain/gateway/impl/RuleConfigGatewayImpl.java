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
import com.jd.cho.rule.engine.domain.atomic.RuleDefExpressionParser;
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
        // code重复性校验
        long count = ruleSceneMapper.count(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(ruleScene.getSceneCode()))
                .and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));
        if (count > 0) {
            throw new BusinessException(BizErrorEnum.SCENE_CODE_IS_EXIST);
        }

        List<String> groupCodes = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.toList());
        groupExistCheck(groupCodes);

        RuleSceneDO ruleSceneDO = RuleSceneConvert.INSTANCE.doToDO(ruleScene);
        AtomicLoginUserComponent.packCreateBaseInfo(ruleSceneDO);
        ruleSceneMapper.insertSelective(ruleSceneDO);

        if (CollectionUtils.isNotEmpty(ruleScene.getRuleSceneActions())) {
            this.createRuleSceneAction(ruleScene.getRuleSceneActions(), ruleScene.getSceneCode());
        }
        return ruleScene.getSceneCode();
    }

    /**
     * 判断groupCode是否真实有效存在
     *
     * @param groupCodes 分组code
     */
    private void groupExistCheck(List<String> groupCodes) {
        if (CollectionUtils.isNotEmpty(groupCodes)) {
            List<RuleFactorGroup> ruleFactorGroups = this.queryRuleFactorGroup(groupCodes);
            if (ruleFactorGroups.size() != groupCodes.size()) {
                throw new BusinessException(BizErrorEnum.GROUP_CODE_IS_NOT_EXIST);
            }
        }
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
            groupCodes.addAll(Arrays.stream(each.getGroupCode().split(Dict.SPLIT)).collect(Collectors.toList()));
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

        List<String> groupCodes = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.toList());
        groupExistCheck(groupCodes);

        ruleScene.setSceneCode(null);
        RuleSceneDO ruleSceneDO = RuleSceneConvert.INSTANCE.doToDO(ruleScene);
        AtomicLoginUserComponent.packUpdateBaseInfo(ruleSceneDO);
        ruleSceneMapper.updateByPrimaryKeySelective(ruleSceneDO);

        if (CollectionUtils.isNotEmpty(ruleScene.getRuleSceneActions())) {
            ruleSceneMapper.selectByPrimaryKey(ruleScene.getId()).ifPresent(each -> {
                this.updateRuleSceneAction(ruleScene.getRuleSceneActions(), each.getSceneCode());
            });
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
            List<RuleSceneActionDO> insert = ruleSceneActions.stream().map(each -> {
                RuleSceneActionDO ruleSceneActionDO = RuleSceneActionConvert.INSTANCE.doToDO(each);
                AtomicLoginUserComponent.packCreateBaseInfo(ruleSceneActionDO);
                ruleSceneActionDO.setSceneCode(sceneCode);
                return ruleSceneActionDO;
            }).collect(Collectors.toList());
            ruleSceneActionMapper.insertMultipleSelective(insert);
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
    public void createRuleFactor(RuleFactor ruleFactor) {
        long count = ruleFactorMapper.count(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(ruleFactor.getFactorCode()))
                .and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (count > 0) {
            throw new BusinessException(BizErrorEnum.CODE_IS_EXIST);
        }

        groupExistCheck(Lists.newArrayList(ruleFactor.getGroupCode()));

        RuleFactorDO ruleFactorDO = RuleFactorConvert.INSTANCE.doToDO(ruleFactor);
        AtomicLoginUserComponent.packCreateBaseInfo(ruleFactorDO);
        ruleFactorMapper.insertSelective(ruleFactorDO);
    }

    @Override
    public void updateRuleFactor(RuleFactor ruleFactor) {
        AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotBlank(ruleFactor.getFactorCode(), BizErrorEnum.DOES_NOT_EXIST);
        groupExistCheck(Lists.newArrayList(ruleFactor.getGroupCode()));

        RuleFactorDO ruleFactorDO = RuleFactorConvert.INSTANCE.doToDO(ruleFactor);
        AtomicLoginUserComponent.packUpdateBaseInfo(ruleFactorDO);
        ruleFactorDO.setFactorCode(null);
        ruleFactorDO.setFactorType(null);
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
        List<RuleFactorDO> ruleFactorList = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(factorCode))
                .and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (CollectionUtils.isEmpty(ruleFactorList)) {
            throw new BusinessException(BizErrorEnum.DOES_NOT_EXIST);
        }
        RuleFactorDO ruleFactorDO = ruleFactorList.get(0);
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
    private Map<String, RuleFactor> getFactorScriptParam(List<RuleCondition> rules) {
        Set<String> factorCodes = Sets.newHashSet();
        rules.forEach(each -> findFactorCodes(each, factorCodes));

        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.yn, isEqualTo(true))
                .and(RuleFactorDynamicSqlSupport.factorCode, isIn(factorCodes)));
        if (ruleFactors.size() != factorCodes.size()) {
            throw new BusinessException(BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
        }

        return ruleFactors.stream().map(RuleFactorConvert.INSTANCE::doToEntity).collect(Collectors.toMap(RuleFactor::getFactorCode, Function.identity()));
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
        List<RuleCondition> ruleConditions = rulePackDTO.getRules().stream().map(each -> JSON.parseObject(JSON.toJSONString(each.getRuleCondition()), RuleCondition.class)).collect(Collectors.toList());
        final Map<String, RuleFactor> factorMaps = getFactorScriptParam(ruleConditions);
        Map<String, List<String>> factorScriptParam = factorMaps.values().stream().collect(Collectors.toMap(RuleFactor::getFactorCode, each -> Arrays.stream(each.getFactorScriptParam().split(Dict.SPLIT)).collect(Collectors.toList())));

        ruleConditions.forEach(ruleCondition -> RuleDefExpressionParser.checkRuleCondition(ruleCondition, factorMaps));


        List<RuleDefDO> ruleDefs = rulePackDTO.getRules().stream()
                .map(o -> RuleDefDO.builder().ruleAction(JSON.toJSONString(o.getRuleActions()))
                        .ruleCondition(JSON.toJSONString(o.getRuleCondition()))
                        .priority(o.getPriority()).creator(loginUserInfo.getLoginUser()).tenant(loginUserInfo.getTenant()).build()).collect(Collectors.toList());
        ruleDefMapper.insertMultipleSelective(ruleDefs);

        String ruleIds = ruleDefs.stream().filter(each -> Objects.nonNull(each.getId())).map(o -> String.valueOf(o.getId())).collect(Collectors.joining(Dict.SPLIT));
        RulePackDO rulePackDO = RulePackConvert.INSTANCE.doToDO(rulePackDTO);
        rulePackDO.setRuleIds(ruleIds);
        rulePackDO.setVersion(version);
        rulePackDO.setPackParams(JSON.toJSONString(factorScriptParam));
        AtomicLoginUserComponent.packCreateBaseInfo(rulePackDO);
        rulePackMapper.insertSelective(rulePackDO);
        return rulePackDTO.getRulePackCode();
    }

//    /**
//     * 检查规则包中配置的规则因子是否存在
//     *
//     * @param factorScriptParam 规则因子集合
//     */
//    private void ruleFactorCheck(Map<String, List<String>> factorScriptParam) {
//        if (Objects.nonNull(factorScriptParam)) {
//            List<String> factorCodes = new ArrayList<>(factorScriptParam.keySet());
//            long validCount = ruleFactorMapper.count(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isIn(factorCodes))
//                    .and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
//            if (validCount != factorCodes.size()) {
//                throw new BusinessException(BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
//            }
//        }
//    }


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
            resultCodes.add(ruleCondition.getOriginalFactorCode());
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
