package com.jd.cho.rule.engine.core.extend;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.spi.RuleDefsExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RuleGroupExtendServiceFactoryImpl implements RuleGroupExtendServiceFactory {
    private List<RuleDefsExecutor> services;

    @Override
    public RuleDefsExecutor get(String ruleArrangeStrategy) {
        if (!StringUtils.hasLength(ruleArrangeStrategy)) {
            return null;
        }

        for (RuleDefsExecutor service : services) {
            if (ruleArrangeStrategy.equals(service.getCode())) {
                return service;
            }
        }

        throw new IllegalArgumentException("ruleArrangeStrategy not found: " + ruleArrangeStrategy);
    }

    @Override
    public List<CommonDict> allRuleGroup() {
        return services.stream().map(this::getRuleGroup).collect(Collectors.toList());
    }

    private CommonDict getRuleGroup(RuleDefsExecutor ruleDefsExecutor) {
        return CommonDict.builder().code(ruleDefsExecutor.getCode()).desc(ruleDefsExecutor.getName()).build();
    }
}
