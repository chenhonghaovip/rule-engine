package com.jd.cho.rule.engine.domain.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.protocol.RulePackDTO;
import com.jd.cho.rule.engine.common.protocol.checker.RulePackDTOChecker;
import com.jd.cho.rule.engine.common.protocol.parser.RulePackDTOFactorParser;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.common.util.AtomicLoginUserUtil;
import com.jd.cho.rule.engine.common.util.AtomicRuleFactorUtil;
import com.jd.cho.rule.engine.common.util.MethodUtil;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.dal.DO.*;
import com.jd.cho.rule.engine.dal.mapper.*;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.*;
import com.jd.cho.rule.engine.infra.convert.*;
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

    public static final Map<String, RuleFactor> RULE_CODE_MAP = Maps.newHashMap();

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

    @Resource
    private RulePackDTOChecker rulePackDTOChecker;
    @Resource
    private RulePackDTOFactorParser rulePackDTOFactorParser;
    @Resource
    private RuleFactorTypeLoader ruleFactorTypeLoader;
    @Resource
    private InfraConverters infraConverters;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRuleScene(RuleScene ruleScene) {
        // code重复性校验
        long count = ruleSceneMapper.count(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(ruleScene.getSceneCode())).and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));
        if (count > 0) {
            throw new BusinessException(BizErrorEnum.SCENE_CODE_IS_EXIST);
        }

        List<String> groupCodes = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.toList());
        groupExistCheck(groupCodes);

        RuleSceneDO ruleSceneDO = RuleSceneDOConvert.INSTANCE.doToDO(ruleScene);
        AtomicLoginUserUtil.packCreateBaseInfo(ruleSceneDO);
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
        List<RuleSceneDO> ruleSceneList = ruleSceneMapper.select(s -> s.where(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));

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
            RuleScene ruleScene = RuleSceneDOConvert.INSTANCE.doToEntity(each);
            List<RuleFactorGroup> groups = Arrays.stream(each.getGroupCode().split(Dict.SPLIT)).map(groupMaps::get).collect(Collectors.toList());
            ruleScene.setRuleSceneActions(sceneActionMap.get(each.getSceneCode()));
            ruleScene.setRuleFactorGroups(groups);
            return ruleScene;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> queryRuleScene(String sceneCode) {
        Optional<RuleSceneDO> optionalRuleScene = ruleSceneMapper.selectOne(s -> s.where(RuleSceneDynamicSqlSupport.sceneCode, isEqualTo(sceneCode)).and(RuleSceneDynamicSqlSupport.yn, isEqualTo(true)));
        if (!optionalRuleScene.isPresent()) {
            return Lists.newArrayList();
        }
        RuleSceneDO ruleSceneDO = optionalRuleScene.get();
        List<String> groupCodes = Arrays.stream(ruleSceneDO.getGroupCode().split(Dict.SPLIT)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(groupCodes)) {
            return Lists.newArrayList();
        }
        return groupCodes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRuleScene(RuleScene ruleScene) {

        List<String> groupCodes = ruleScene.getRuleFactorGroups().stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.toList());
        groupExistCheck(groupCodes);

        ruleScene.setSceneCode(null);
        RuleSceneDO ruleSceneDO = RuleSceneDOConvert.INSTANCE.doToDO(ruleScene);
        AtomicLoginUserUtil.packUpdateBaseInfo(ruleSceneDO);
        ruleSceneMapper.updateByPrimaryKeySelective(ruleSceneDO);

        if (CollectionUtils.isNotEmpty(ruleScene.getRuleSceneActions())) {
            ruleSceneMapper.selectByPrimaryKey(ruleScene.getId()).ifPresent(each -> {
                this.updateRuleSceneAction(ruleScene.getRuleSceneActions(), each.getSceneCode());
            });
        }
    }

    @Override
    public List<RuleSceneAction> queryRuleSceneAction(List<String> sceneCodes) {
        List<RuleSceneActionDO> ruleSceneActionDOList = ruleSceneActionMapper.select(s -> s.where(RuleSceneActionDynamicSqlSupport.sceneCode, isIn(sceneCodes)).and(RuleSceneActionDynamicSqlSupport.yn, isEqualTo(true)));
        return ruleSceneActionDOList.stream().map(RuleSceneActionDOConvert.INSTANCE::doToEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode) {
        if (CollectionUtils.isNotEmpty(ruleSceneActions)) {
            List<RuleSceneActionDO> insert = ruleSceneActions.stream().map(each -> {
                RuleSceneActionDO ruleSceneActionDO = RuleSceneActionDOConvert.INSTANCE.doToDO(each);
                AtomicLoginUserUtil.packCreateBaseInfo(ruleSceneActionDO);
                ruleSceneActionDO.setSceneCode(sceneCode);
                return ruleSceneActionDO;
            }).collect(Collectors.toList());
            ruleSceneActionMapper.insertMultipleSelective(insert);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode) {
        ruleSceneActionMapper.update(s -> s.set(RuleSceneActionDynamicSqlSupport.yn).equalTo(false).set(RuleSceneActionDynamicSqlSupport.modifier).equalTo(AtomicLoginUserUtil.getLoginUser()).set(RuleSceneActionDynamicSqlSupport.modifyTime).equalTo(new Date()).where(RuleSceneActionDynamicSqlSupport.sceneCode, isEqualTo(sceneCode)).and(RuleSceneActionDynamicSqlSupport.yn, isEqualTo(true)));
        if (CollectionUtils.isNotEmpty(ruleSceneActions)) {
            this.createRuleSceneAction(ruleSceneActions, sceneCode);
        }
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroup() {
        UserInfo loginUserInfo = AtomicLoginUserUtil.getLoginUserInfo();
        List<RuleFactorGroupDO> list = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.yn, isEqualTo(true)).and(RuleFactorGroupDynamicSqlSupport.tenant, isEqualTo(loginUserInfo.getTenant())));
        return list.stream().map(RuleFactorGroupDOConvert.INSTANCE::doToEntity).collect(Collectors.toList());
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroup(List<String> groupCodes) {
        List<RuleFactorGroupDO> allGroups = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.yn, isEqualTo(true)).and(RuleFactorGroupDynamicSqlSupport.groupCode, isIn(groupCodes)));
        return allGroups.stream().map(RuleFactorGroupDOConvert.INSTANCE::doToEntity).collect(Collectors.toList());
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroupWithChildren(List<String> groupCodes) {
        List<RuleFactorGroupDO> allGroups = ruleFactorGroupMapper.select(s -> s.where(RuleFactorGroupDynamicSqlSupport.yn, isEqualTo(true)));
        List<RuleFactorGroup> allGroupEntities = allGroups.stream().map(RuleFactorGroupDOConvert.INSTANCE::doToEntity).collect(Collectors.toList());

        List<RuleFactorGroup> rootGroups = allGroupEntities.stream().filter(each -> groupCodes.contains(each.getGroupCode())).collect(Collectors.toList());

        List<RuleFactorGroup> result = Lists.newArrayList(rootGroups);
        rootGroups.forEach(each -> findChildren(each, allGroupEntities, result));
        return result;
    }

    @Override
    public List<RuleFactorGroup> queryRuleFactorGroupWithTree(List<String> groupCodes) {
        List<RuleFactorGroup> allGroupEntities = queryRuleFactorGroupWithChildren(groupCodes);
        List<RuleFactorGroup> rootGroups = allGroupEntities.stream().filter(each -> groupCodes.contains(each.getGroupCode())).collect(Collectors.toList());
        rootGroups.forEach(each -> buildGroupTree(each, allGroupEntities));
        return rootGroups;
    }

    private void findChildren(RuleFactorGroup root, List<RuleFactorGroup> allChildren, List<RuleFactorGroup> result) {
        if (CollectionUtils.isNotEmpty(allChildren)) {
            List<RuleFactorGroup> groups = allChildren.stream().filter(each -> root.getGroupCode().equals(each.getParentGroupCode())).collect(Collectors.toList());
            result.addAll(groups);
            groups.forEach(each -> findChildren(each, allChildren, result));
        }
    }

    /**
     * 构建树形结构
     *
     * @param root      根节点
     * @param allChilds 所有叶子节点
     */
    private void buildGroupTree(RuleFactorGroup root, List<RuleFactorGroup> allChilds) {
        if (CollectionUtils.isNotEmpty(allChilds)) {
            List<RuleFactorGroup> groups = allChilds.stream().filter(each -> root.getGroupCode().equals(each.getParentGroupCode())).collect(Collectors.toList());
            root.setRuleFactorGroups(groups);
            groups.forEach(each -> buildGroupTree(each, allChilds));
        }
    }

    @Override
    public String createRuleFactorGroup(RuleFactorGroup ruleFactorGroup) {
        if (StringUtils.isBlank(ruleFactorGroup.getGroupCode())) {
            ruleFactorGroup.setGroupCode(UUID.randomUUID().toString());
        }
        UserInfo loginUserInfo = AtomicLoginUserUtil.getLoginUserInfo();
        RuleFactorGroupDO ruleFactorGroupDO = RuleFactorGroupDO.builder().groupCode(ruleFactorGroup.getGroupCode()).groupName(ruleFactorGroup.getGroupName()).creator(loginUserInfo.getLoginUser()).tenant(loginUserInfo.getTenant()).build();
        ruleFactorGroupMapper.insertSelective(ruleFactorGroupDO);
        return ruleFactorGroup.getGroupCode();
    }

    @Override
    public void createRuleFactor(RuleFactor ruleFactor) {
        long count = ruleFactorMapper.count(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(ruleFactor.getFactorCode())).and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (count > 0) {
            throw new BusinessException(BizErrorEnum.CODE_IS_EXIST);
        }

        groupExistCheck(Lists.newArrayList(ruleFactor.getGroupCode()));

        RuleFactorDO ruleFactorDO = RuleFactorDOConvert.INSTANCE.doToDO(ruleFactor);
        AtomicLoginUserUtil.packCreateBaseInfo(ruleFactorDO);
        ruleFactorMapper.insertSelective(ruleFactorDO);

        RULE_CODE_MAP.put(ruleFactor.getFactorCode(), ruleFactor);
    }

    @Override
    public void updateRuleFactor(RuleFactor ruleFactor) {
        AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotBlank(ruleFactor.getFactorCode(), BizErrorEnum.DOES_NOT_EXIST);
        groupExistCheck(Lists.newArrayList(ruleFactor.getGroupCode()));

        RuleFactorDO ruleFactorDO = RuleFactorDOConvert.INSTANCE.doToDO(ruleFactor);
        AtomicLoginUserUtil.packUpdateBaseInfo(ruleFactorDO);
        ruleFactorDO.setFactorCode(null);
        ruleFactorDO.setFactorType(null);
        ruleFactorMapper.updateByPrimaryKeySelective(ruleFactorDO);

        RULE_CODE_MAP.put(ruleFactor.getFactorCode(), ruleFactor);
    }

    @Override
    public List<RuleFactor> queryFactorBySceneCode(List<String> groupCodes, Map<String, Object> context) {
        List<RuleFactorGroup> ruleFactorGroups = this.queryRuleFactorGroupWithChildren(groupCodes);
        List<String> allGroupCodes = ruleFactorGroups.stream().map(RuleFactorGroup::getGroupCode).collect(Collectors.toList());

        Map<String, RuleFactorGroup> groupMaps = ruleFactorGroups.stream().collect(Collectors.toMap(RuleFactorGroup::getGroupCode, Function.identity()));
        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.groupCode, isIn(allGroupCodes)).and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));

        List<RuleFactor> ruleFactorList = ruleFactors.stream().map(each -> {
            RuleFactor ruleFactor = infraConverters.doToEntity(each);
            ruleFactor.setGroupName(groupMaps.get(each.getGroupCode()).getGroupName());
            ruleFactor.setParentGroupCode(groupMaps.get(each.getGroupCode()).getParentGroupCode());
            ruleFactor.setConstantValues(MethodUtil.getDict(each.getConstantType(), each.getConstantValue(), context));
            FactorTypeDTO factorTypeDTO = ruleFactorTypeLoader.getFactorTypeDTO(each.getFactorType());
            if (Objects.isNull(factorTypeDTO)) {
                throw new BusinessException(BizErrorEnum.FACTOR_TYPE_IS_ERROR);
            }
            ruleFactor.setExpressOperationList(factorTypeDTO.getComparativeOperatorList());
            return ruleFactor;
        }).collect(Collectors.toList());

        return AtomicRuleFactorUtil.extendFactors(ruleFactorList, context);
    }

    @Override
    public List<RuleFactor> queryFactorCodes() {
        if (RULE_CODE_MAP.isEmpty()) {
            List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.tenant, isEqualTo(AtomicLoginUserUtil.getLoginUserInfo().getTenant())).and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
            List<RuleFactor> ruleFactorList = ruleFactors.stream().map(infraConverters::doToEntity).collect(Collectors.toList());
            RULE_CODE_MAP.putAll(ruleFactorList.stream().collect(Collectors.toMap(RuleFactor::getFactorCode, Function.identity())));
            return ruleFactorList;
        }
        return new ArrayList<>(RULE_CODE_MAP.values());
    }

    @Override
    public List<CommonDict> factorConstantValues(Map<String, Object> context) {
        String factorCode = (String) context.get(Dict.FACTOR_CODE);
        List<RuleFactorDO> ruleFactorList = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(factorCode)).and(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)));
        if (CollectionUtils.isEmpty(ruleFactorList)) {
            throw new BusinessException(BizErrorEnum.DOES_NOT_EXIST);
        }
        RuleFactorDO ruleFactorDO = ruleFactorList.get(0);
        return MethodUtil.getDict(ruleFactorDO.getConstantType(), ruleFactorDO.getConstantValue(), context);
    }

    @Override
    public RulePack rulePackInfo(String rulePackCode) {
        Optional<RulePackDO> optionalRulePackDO = rulePackMapper.selectOne(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackCode)).and(RulePackDynamicSqlSupport.yn, isEqualTo(true)).and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
        if (optionalRulePackDO.isPresent()) {
            RulePackDO rulePackDO = optionalRulePackDO.get();
            return RulePackDOConvert.INSTANCE.doToEntity(rulePackDO);
        }
        return null;
    }

    @Override
    public List<RulePack> historyRulePackInfo(String rulePackCode) {
        List<RulePackDO> history = rulePackMapper.select(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackCode)).and(RulePackDynamicSqlSupport.yn, isEqualTo(true)).orderBy(RulePackDynamicSqlSupport.version.descending()));
        return history.stream().map(RulePackDOConvert.INSTANCE::doToEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRulePack(RulePackDTO rulePackDTO) {
        if (Objects.nonNull(rulePackDTO)) {
            long count = rulePackMapper.count(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(rulePackDTO.getRulePackCode())).and(RulePackDynamicSqlSupport.yn, isEqualTo(true)));
            if (count > 0) {
                throw new BusinessException("规则包code重复");
            }

            String rulePackCode = StringUtils.isNotBlank(rulePackDTO.getRulePackCode()) ? rulePackDTO.getRulePackCode() : UUID.randomUUID().toString();
            rulePackDTO.setRulePackCode(rulePackCode);
            return insertRuleGroup(rulePackDTO, 1);
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

                UserInfo loginUserInfo = AtomicLoginUserUtil.getLoginUserInfo();
                int update = rulePackMapper.update(s -> s.set(RulePackDynamicSqlSupport.modifyTime).equalTo(new Date()).set(RulePackDynamicSqlSupport.modifier).equalTo(loginUserInfo.getLoginUser()).set(RulePackDynamicSqlSupport.latest).equalTo(0).where(RulePackDynamicSqlSupport.id, isEqualTo(id)).and(RulePackDynamicSqlSupport.latest, isEqualTo(1)));
                if (update == 0) {
                    throw new BusinessException(DATA_HAS_CHANGE);
                }

                List<RulePackDO> allVersionList = rulePackMapper.select(s -> s.where(RulePackDynamicSqlSupport.rulePackCode, isEqualTo(each.getRulePackCode())));
                Integer maxVersion = allVersionList.stream().map(RulePackDO::getVersion).max(Integer::compareTo).orElse(1);

                rulePackDTO.setRulePackCode(each.getRulePackCode());
                insertRuleGroup(rulePackDTO, maxVersion + 1);
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
     * @param rulePackDTO 变量信息
     * @return key:因子-value:因子入参
     */
    private Map<String, RuleFactor> getFactorScriptParam(RulePackDTO rulePackDTO) {
        Set<String> factorCodes = rulePackDTOFactorParser.getFactorScriptParam(rulePackDTO);

        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.yn, isEqualTo(true)).and(RuleFactorDynamicSqlSupport.factorCode, isIn(factorCodes)));
        if (ruleFactors.size() != factorCodes.size()) {
            throw new BusinessException(BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
        }

        return ruleFactors.stream().map(infraConverters::doToEntity).collect(Collectors.toMap(RuleFactor::getFactorCode, Function.identity()));
    }


    /**
     * 添加规则包信息
     *
     * @param rulePackDTO 规则包
     * @param version     规则包版本
     * @return 规则包编号
     */
    private String insertRuleGroup(RulePackDTO rulePackDTO, int version) {
        final Map<String, RuleFactor> factorMaps = getFactorScriptParam(rulePackDTO);

        rulePackDTOChecker.check(rulePackDTO, factorMaps);

        Map<String, List<String>> factorScriptParam = factorMaps.values().stream().filter(each -> StringUtils.isNotBlank(each.getFactorScriptParam())).collect(Collectors.toMap(RuleFactor::getFactorCode, each -> Arrays.stream(each.getFactorScriptParam().split(Dict.SPLIT)).collect(Collectors.toList())));

        RulePackDO rulePackDO = RulePackDOConvert.INSTANCE.doToDO(rulePackDTO);
        rulePackDO.setVersion(version);
        rulePackDO.setPackParams(JSON.toJSONString(factorScriptParam));
        AtomicLoginUserUtil.packCreateBaseInfo(rulePackDO);
        rulePackMapper.insertSelective(rulePackDO);
        return rulePackDTO.getRulePackCode();
    }

}
