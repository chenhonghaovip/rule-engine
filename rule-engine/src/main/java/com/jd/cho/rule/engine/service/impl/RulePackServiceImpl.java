package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.convert.RulePackConvert;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.group.PriorityOrderMatchRuleGroup;
import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
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
        String ruleArrangeStrategy = StringUtils.isBlank(rulePackDTO.getRuleArrangeStrategy()) ? PriorityOrderMatchRuleGroup.CODE : rulePackDTO.getRuleArrangeStrategy();
        rulePackDTO.setRuleArrangeStrategy(ruleArrangeStrategy);

        return ruleConfigGateway.createRulePack(rulePackDTO);
    }

    @Override
    public void updateRule(RulePackDTO rulePackDTO) {
        Assert.notNull(rulePackDTO, "规则数据不能为空");
        Assert.notNull(rulePackDTO.getId(), "规则包id不能为空");
        String ruleArrangeStrategy = StringUtils.isBlank(rulePackDTO.getRuleArrangeStrategy()) ? PriorityOrderMatchRuleGroup.CODE : rulePackDTO.getRuleArrangeStrategy();
        rulePackDTO.setRuleArrangeStrategy(ruleArrangeStrategy);
        ruleConfigGateway.updateRulePack(rulePackDTO);
    }


    @Override
    public RulePackDTO onlineRulePack(String rulePackCode) {
        RulePack rulePack = ruleConfigGateway.rulePackInfo(rulePackCode);
        return RulePackConvert.INSTANCE.doToDTO(rulePack);
    }

    @Override
    public List<RulePackDTO> rulePackHistory(String rulePackCode) {
        List<RulePack> rulePacks = ruleConfigGateway.historyRulePackInfo(rulePackCode);
        return rulePacks.stream().map(RulePackConvert.INSTANCE::doToDTO).collect(Collectors.toList());
    }

    @Override
    public List<String> queryParams(String rulePackCode) {
        return null;
    }
}
