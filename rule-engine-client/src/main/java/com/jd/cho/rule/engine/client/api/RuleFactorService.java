package com.jd.cho.rule.engine.client.api;

import com.jd.cho.rule.engine.client.dto.RuleFactorEditDTO;
import com.jd.cho.rule.engine.client.dto.RuleFactorQueryDTO;

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
    String createRuleFactor(RuleFactorEditDTO ruleFactorDTO);


    /**
     * 更新规则因子
     *
     * @param ruleFactorDTO 规则因子
     */
    void updateRuleFactor(RuleFactorEditDTO ruleFactorDTO);


    /**
     * 通过场景code获取对应的因子信息
     *
     * @param sceneCode 场景code
     * @return 规则因子集合
     */
    List<RuleFactorQueryDTO> queryBySceneCode(String sceneCode);



}
