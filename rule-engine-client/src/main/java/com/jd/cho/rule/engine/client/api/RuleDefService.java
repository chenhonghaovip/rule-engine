package com.jd.cho.rule.engine.client.api;

import com.jd.cho.rule.engine.common.client.dto.RuleDefDTO;
import com.jd.cho.rule.engine.common.client.dto.RuleDefQueryDTO;

import java.util.List;

/**
 * 规则定义服务
 *
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleDefService {

    /**
     * 新增规则
     *
     * @param ruleDefDTO 规则定义
     * @return 规则码
     */
    String createRule(RuleDefDTO ruleDefDTO);


    /**
     * 批量插入规则信息
     *
     * @param list 规则集合
     * @return 规则码集合
     */
    List<RuleDefDTO> batchCreateRule(List<RuleDefDTO> list);


    /**
     * 更新规则信息
     *
     * @param ruleDefDTO 规则定义
     */
    void updateRule(RuleDefDTO ruleDefDTO);


    /**
     * 批量更新规则信息
     *
     * @param list 规则集合
     */
    void batchUpdateRule(List<RuleDefDTO> list);


    /**
     * 查询全部最新的规则
     *
     * @param ruleCodes 规则code集合
     * @return 规则列表
     */
    List<RuleDefQueryDTO> queryByRuleCodes(List<String> ruleCodes);


    /**
     * 查询当前规则的历史信息
     *
     * @param ruleCode ruleCode
     * @return 规则列表
     */
    List<RuleDefQueryDTO> queryByRuleCodes(String ruleCode);


    /**
     * 查询这些规则中需要的参数
     *
     * @param ruleCodes 规则code
     * @return 参数集合
     */
    List<String> queryParams(List<String> ruleCodes);

}
