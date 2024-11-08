package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleConfigGateway {

    /**
     * 通过场景code获取规则因子
     *
     * @param sceneCode 场景code
     * @return 规则因子
     */
    List<RuleFactor> queryBySceneCode(String sceneCode);

    /**
     * 查询规则包内规则定义
     *
     * @param rulePackCode 规则包编码
     * @return 规则定义信息
     */
    RulePack rulePackInfo(String rulePackCode);

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
}
