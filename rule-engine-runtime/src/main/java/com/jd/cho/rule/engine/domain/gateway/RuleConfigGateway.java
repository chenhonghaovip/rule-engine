package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.protocol.RulePackDTO;
import com.jd.cho.rule.engine.domain.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleConfigGateway {

    /**---------------------------------------           规则场景start                     --------------------------------------*/
    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<RuleScene> queryRuleScene();

    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<String> queryRuleScene(String sceneCode);

    /**
     * 创建规则场景
     *
     * @return List<RuleScene>
     */
    String createRuleScene(RuleScene ruleScene);

    /**
     * 编辑规则场景
     */
    void updateRuleScene(RuleScene ruleScene);


    /**---------------------------------------           规则场景end                     --------------------------------------*/

    /**---------------------------------------           规则动作start                     --------------------------------------*/
    /**
     * 查询规则动作
     *
     * @return List<RuleScene>
     */
    List<RuleSceneAction> queryRuleSceneAction(List<String> sceneCodes);

    /**
     * 创建规则动作
     */
    void createRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode);

    /**
     * 编辑规则动作
     */
    void updateRuleSceneAction(List<RuleSceneAction> ruleSceneActions, String sceneCode);
    /**---------------------------------------           规则动作end                     --------------------------------------*/


    /**---------------------------------------           规则因子分组start                     --------------------------------------*/
    /**
     * 查询规则因子分组
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroup();

    /**
     * 查询规则因子分组
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroup(List<String> groupCodes);

    /**
     * 查询规则因子分组（转换为树形结构）
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroupWithChildren(List<String> groupCodes);

    /**
     * 查询规则因子分组（转换为树形结构）
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroupWithTree(List<String> groupCodes);

    /**
     * 创建规则因子分组
     *
     * @return List<RuleScene>
     */
    String createRuleFactorGroup(RuleFactorGroup ruleFactorGroup);

    /**---------------------------------------           规则因子分组end                     --------------------------------------*/


    /**---------------------------------------           规则因子start                     --------------------------------------*/

    /**
     * 创建规则因子
     */
    void createRuleFactor(RuleFactor ruleFactor);

    /**
     * 编辑规则因子（编辑时，规则因子的code和类型不能修改，否则可能会导致已生效的规则异常）
     */
    void updateRuleFactor(RuleFactor ruleFactor);

    /**
     * 通过场景code获取规则因子
     *
     * @param groupCodes 分组code
     * @return 规则因子
     */
    List<RuleFactor> queryFactorBySceneCode(List<String> groupCodes, Map<String, Object> context);

    /**
     * 查询当前租户下全部的规则因子
     *
     * @return 规则因子
     */
    List<RuleFactor> queryFactorCodes();


    /**
     * 获取规则因子的下拉列表值
     *
     * @param context 场景code、规则因子常量值所需参数等
     * @return list
     */
    List<CommonDict> factorConstantValues(Map<String, Object> context);

    /**---------------------------------------           规则因子end                     --------------------------------------*/


    /**---------------------------------------           规则start                     --------------------------------------*/
    /**
     * 查询规则包内规则定义
     *
     * @param rulePackCode 规则包编码
     * @return 规则定义信息
     */
    RulePack rulePackInfo(String rulePackCode);

    /**
     * 历史信息
     *
     * @param rulePackCode 规则包编码
     * @return 规则定义信息
     */
    List<RulePack> historyRulePackInfo(String rulePackCode);

    /**
     * 新增规则包信息
     *
     * @param rulePackDTO 规则集合
     */
    String createRulePack(RulePackDTO rulePackDTO);

    /**
     * 更新规则包信息
     *
     * @param rulePackDTO 规则集合
     */
    void updateRulePack(RulePackDTO rulePackDTO);


    /**
     * 查询规则包中规则计算所需参数
     *
     * @param rulePackCode rulePackCode
     * @return 参数集合
     */
    Map<String, List<String>> queryFactorParamsWithMap(String rulePackCode);


    /**
     * 查询规则包中规则计算所需参数
     *
     * @param rulePackCode rulePackCode
     * @return 参数集合
     */
    Set<String> queryFactorParams(String rulePackCode);

    /**
     * 查询规则包中涉及的因子
     *
     * @param rulePackCode rulePackCode
     * @return 参数集合
     */
    Set<String> queryFactors(String rulePackCode);
    /**---------------------------------------           规则end                     --------------------------------------*/


}
