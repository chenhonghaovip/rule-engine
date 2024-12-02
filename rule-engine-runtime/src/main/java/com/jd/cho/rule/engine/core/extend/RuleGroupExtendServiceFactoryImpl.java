package com.jd.cho.rule.engine.core.extend;

import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RuleGroupExtendServiceFactoryImpl implements RuleGroupExtendServiceFactory {
    private List<RuleGroupExtendService> services;

    @Override
    public RuleGroupExtendService get(String ruleArrangeStrategy) {
        if (!StringUtils.hasLength(ruleArrangeStrategy)) {
            return null;
        }

        for (RuleGroupExtendService service : services) {
            if (ruleArrangeStrategy.equals(service.getCode())) {
                return service;
            }
        }

        throw new IllegalArgumentException("ruleArrangeStrategy not found: " + ruleArrangeStrategy);
    }
}
