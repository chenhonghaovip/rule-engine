package com.jd.cho.rule.engine.domain.rule;

import com.jd.cho.rule.engine.domain.model.RuleDef;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleDomainService {

    /**
     * 获取规则场景信息
     *
     * @return 规则列表信息
     */
    List<String> allScenes();

    /**
     * 添加规则场景
     *
     * @return 规则场景id
     */
    long addScene();


    /**
     * 查询规则定义
     *
     * @param ruleCodes 规则编码
     * @return 规则定义信息
     */
    List<RuleDef> ruleDefQuery(List<String> ruleCodes);

}
