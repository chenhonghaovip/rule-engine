package com.jd.cho.rule.engine.client.api;

import com.jd.cho.rule.engine.client.dto.RuleFactorDTO;

import java.util.List;

/**
 * 规则因子服务
 *
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleFactorService {
    /**
     * 创建规则因子
     *
     * @param ruleFactorDTO 规则因子
     * @return 规则因子ID
     */
    String createRuleFactor(RuleFactorDTO ruleFactorDTO);

    /**
     * 批量创建规则因子
     *
     * @param ruleFactorDTOs 规则因子
     */
    List<RuleFactorDTO> batchCreateRuleFactor(List<RuleFactorDTO> ruleFactorDTOs);


    /**
     * 更新规则因子
     *
     * @param ruleFactorDTO 规则因子
     */
    void updateRuleFactor(RuleFactorDTO ruleFactorDTO);

    /**
     * 批量更新规则因子
     *
     * @param ruleFactorDTOs 规则因子
     */
    void batchUpdateRuleFactor(List<RuleFactorDTO> ruleFactorDTOs);

    /**
     * 根据规则编码批量查询规则因子
     *
     * @param ruleCodes 规则编码
     * @return 规则因子列表
     */
    List<RuleFactorDTO> queryByRuleCodes(List<String> ruleCodes);

    /**
     * 根据规则因子编码批量查询规则因子
     *
     * @param factorCodes 规则因子编码
     * @return 规则因子列表
     */
    public List<RuleFactorDTO> queryByFactorCodes(List<String> factorCodes);


}
