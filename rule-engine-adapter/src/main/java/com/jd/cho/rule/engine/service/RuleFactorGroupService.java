package com.jd.cho.rule.engine.service;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorGroupReq;
import com.jd.cho.rule.engine.domain.model.RuleFactorGroup;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleFactorGroupService {
    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<RuleFactorGroup> queryRuleFactorGroup();


    /**
     * 通过场景code获取对应的因子信息
     *
     * @param sceneCode 场景code
     * @return 规则因子集合
     */
    List<RuleFactorGroup> queryGroupBySceneCode(String sceneCode);

    /**
     * 创建规则因子分组
     *
     * @param ruleFactorGroupReq ruleFactorGroupReq
     * @return List<RuleScene>
     */
    String createRuleFactorGroup(RuleFactorGroupReq ruleFactorGroupReq);


}
