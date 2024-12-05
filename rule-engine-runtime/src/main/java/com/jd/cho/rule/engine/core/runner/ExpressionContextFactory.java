package com.jd.cho.rule.engine.core.runner;

import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ExpressionContextFactory {
    private final ApplicationContext applicationContext;
    private final FactorValueService factorValueService;

    public ExpressionContext create(Map<String, Object> map, Map<String, String> fieldMapping) {
        return new ExpressionContext(map, fieldMapping, applicationContext, factorValueService);
    }
}
