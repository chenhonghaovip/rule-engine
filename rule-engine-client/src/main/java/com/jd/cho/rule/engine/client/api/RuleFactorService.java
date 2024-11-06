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
     * 更新规则因子
     *
     * @param ruleFactorDTO 规则因子
     */
    void updateRuleFactor(RuleFactorDTO ruleFactorDTO);


    /**
     * 通过场景code获取对应的因子信息
     *
     * @param sceneCode 场景code
     * @return 规则因子集合
     */
    List<RuleFactorDTO> queryBySceneCode(String sceneCode);


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
    List<RuleFactorDTO> queryByFactorCodes(List<String> factorCodes);


}
