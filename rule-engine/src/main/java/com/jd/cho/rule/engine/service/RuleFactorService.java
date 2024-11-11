package com.jd.cho.rule.engine.service;

import com.jd.cho.rule.engine.service.dto.RuleFactorDTO;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;

import java.util.List;
import java.util.Map;

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
     * @param context 上下文参数
     * @return 规则因子集合
     */
    List<RuleFactorQueryDTO> queryBySceneCode(Map<String, Object> context);


}
