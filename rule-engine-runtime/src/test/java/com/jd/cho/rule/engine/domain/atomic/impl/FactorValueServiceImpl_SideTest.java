package com.jd.cho.rule.engine.domain.atomic.impl;

import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class FactorValueServiceImpl_SideTest {

    private RuleConfigGateway ruleConfigGateway;

    FactorValueServiceImpl tester;

    @BeforeEach
    void setUp() {
        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        tester = new FactorValueServiceImpl(ruleConfigGateway);
    }

    @Test
    void testCheck() {
        Mockito.when(ruleConfigGateway.queryFactorCodes()).thenReturn(Collections.emptyList());

        final Object fieldName = null;
        final Map<String, Object> context = new HashMap<>();
        final Map<String, String> fieldMapping = new HashMap<>();

        Assertions.assertThatThrownBy(() -> {
            Object fieldValue = tester.getFieldValue(fieldName, context, fieldMapping);
        }).isExactlyInstanceOf(BusinessException.class).satisfies(e -> {
            log.error("ss", e);
        });
    }
}