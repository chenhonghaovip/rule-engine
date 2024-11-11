package com.jd.cho.rule.engine.service;

import com.jd.cho.rule.engine.service.dto.RulePackDTO;

import java.util.List;

/**
 * 规则定义服务
 *
 * @author chenhonghao12
 * @version 1.0
 */
public interface RulePackService {

    /**
     * 新增规则包
     *
     * @param rulePackDTO 规则包信息
     * @return 规则包code
     */
    String createRule(RulePackDTO rulePackDTO);


    /**
     * 更新规则信息
     *
     * @param rulePackDTO 规则定义
     */
    void updateRule(RulePackDTO rulePackDTO);

    /**
     * 查询全部最新的规则
     *
     * @param rulePackCodes 规则code集合
     * @return 规则列表
     */
    RulePackDTO onlineRulePack(String rulePackCodes);


    /**
     * 查询当前规则的历史信息
     *
     * @param rulePackCode ruleCode
     * @return 规则列表
     */
    List<RulePackDTO> rulePackHistory(String rulePackCode);


    /**
     * 查询这些规则中需要的参数
     *
     * @param rulePackCode 规则包code
     * @return 参数集合
     */
    List<String> queryParams(String rulePackCode);

}
