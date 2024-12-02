package com.jd.cho.rule.engine.core.extend;

import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.spi.RuleDefsExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
}
