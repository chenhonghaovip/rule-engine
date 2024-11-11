package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.domain.model.*;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;

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
    String createRuleScene(RuleScene ruleScene);

    /**
     * 查询规则场景
     */
    void updateRuleScene(RuleScene ruleScene);


    /**---------------------------------------           规则场景end                     --------------------------------------*/


    /**---------------------------------------           规则动作start                     --------------------------------------*/
    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<RuleSceneAction> queryRuleSceneAction(String sceneCode);

    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    String createRuleSceneAction(RuleSceneAction ruleSceneAction);

    /**
     * 查询规则场景
     */
    void updateRuleSceneAction(RuleSceneAction ruleSceneAction);
    /**---------------------------------------           规则动作end                     --------------------------------------*/


    /**---------------------------------------           规则因子分组start                     --------------------------------------*/
    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroup();

    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    String createRuleFactorGroup(RuleFactorGroup ruleFactorGroup);

    /**
     * 查询规则场景
     */
    void updateRuleFactorGroup(RuleFactorGroup ruleFactorGroup);
    /**---------------------------------------           规则因子分组end                     --------------------------------------*/


    /**---------------------------------------           规则因子start                     --------------------------------------*/

    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    String createRuleFactor(RuleFactor ruleFactor);

    /**
     * 查询规则场景
     */
    void updateRuleFactor(RuleFactor ruleFactor);

    /**
     * 通过场景code获取规则因子
     *
     * @param sceneCode 场景code
     * @return 规则因子
     */
    List<RuleFactor> queryBySceneCode(String sceneCode, Map<String, Object> context);


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
     * 批量插入规则包信息
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
