package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.adapter.convert.RulePackConvert;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.protocol.RulePackDTO;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.core.executer.PriorityOrderMatchRuleDefsExecutor;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.RulePackService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RulePackServiceImpl implements RulePackService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createRule(RulePackDTO rulePackDTO) {
        Assert.notNull(rulePackDTO, "规则数据不能为空");
        if (StringUtils.isBlank(rulePackDTO.getRulePackCode())) {
            rulePackDTO.setRulePackCode(UUID.randomUUID().toString());
        }
        Assert.notNull(rulePackDTO.getRulePackType(), "规则类型不能为空");
        String ruleArrangeStrategy = StringUtils.isBlank(rulePackDTO.getRuleArrangeStrategy()) ? PriorityOrderMatchRuleDefsExecutor.CODE : rulePackDTO.getRuleArrangeStrategy();
        rulePackDTO.setRuleArrangeStrategy(ruleArrangeStrategy);
        AssertUtil.isNotBlank(rulePackDTO.getRuleContent());

        return ruleConfigGateway.createRulePack(rulePackDTO);
    }

    @Override
    public void updateRule(RulePackDTO rulePackDTO) {
        Assert.notNull(rulePackDTO, "规则数据不能为空");
        Assert.notNull(rulePackDTO.getId(), "规则包id不能为空");
        AssertUtil.isNotBlank(rulePackDTO.getRuleContent());
        String ruleArrangeStrategy = StringUtils.isBlank(rulePackDTO.getRuleArrangeStrategy()) ? PriorityOrderMatchRuleDefsExecutor.CODE : rulePackDTO.getRuleArrangeStrategy();
        rulePackDTO.setRuleArrangeStrategy(ruleArrangeStrategy);
        ruleConfigGateway.updateRulePack(rulePackDTO);
    }


    @Override
    public RulePackDTO onlineRulePack(String rulePackCode) {
        AssertUtil.isNotBlank(rulePackCode, BizErrorEnum.RULE_PACK_CODE_IS_BLANK);
        RulePack rulePack = ruleConfigGateway.rulePackInfo(rulePackCode);
        return RulePackConvert.INSTANCE.doToDTO(rulePack);
    }

    @Override
    public List<RulePackDTO> rulePackHistory(String rulePackCode) {
        AssertUtil.isNotBlank(rulePackCode, BizErrorEnum.RULE_PACK_CODE_IS_BLANK);
        List<RulePack> rulePacks = ruleConfigGateway.historyRulePackInfo(rulePackCode);
        return rulePacks.stream().map(RulePackConvert.INSTANCE::doToDTO).collect(Collectors.toList());
    }

    @Override
    public List<String> queryParams(String rulePackCode) {
        AssertUtil.isNotBlank(rulePackCode, BizErrorEnum.RULE_PACK_CODE_IS_BLANK);
        Set<String> params = ruleConfigGateway.queryFactorParams(rulePackCode);
        return new ArrayList<>(params);
    }
}
