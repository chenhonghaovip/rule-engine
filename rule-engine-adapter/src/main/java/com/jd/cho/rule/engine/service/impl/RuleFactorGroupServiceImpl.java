package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorGroupReq;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleFactorGroup;
import com.jd.cho.rule.engine.service.RuleFactorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleFactorGroupServiceImpl implements RuleFactorGroupService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;


    @Override
    public List<RuleFactorGroup> queryRuleFactorGroup() {
        return ruleConfigGateway.queryRuleFactorGroup();
    }

    @Override
    public String createRuleFactorGroup(RuleFactorGroupReq ruleFactorGroupReq) {
        RuleFactorGroup ruleFactorGroup = RuleFactorGroup.builder().groupCode(ruleFactorGroupReq.getGroupCode())
                .groupName(ruleFactorGroupReq.getGroupName()).build();
        Assert.notNull(ruleFactorGroup, "数据不能为空");
        return ruleConfigGateway.createRuleFactorGroup(ruleFactorGroup);
    }
}
