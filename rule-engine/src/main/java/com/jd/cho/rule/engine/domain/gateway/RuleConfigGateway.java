package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;

import java.util.List;

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
    String createRule(RulePackDTO rulePackDTO);

    /**
     * 更新规则包信息
     *
     * @param rulePackDTO 规则集合
     */
    void batchUpdateRule(RulePackDTO rulePackDTO);


    /**
     * 查询这些规则中需要的参数
     *
     * @param ruleCodes 规则code
     * @return 参数集合
     */
    List<String> queryParams(List<String> ruleCodes);

}
