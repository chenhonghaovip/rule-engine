package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.core.dispatch.CoreDispatchRulePackExecutor;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RulePack;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
class CoreDispatchRulePackExecutorImpl_SideTest {

    private RuleConfigGateway ruleConfigGateway;
    private List<AcceptableRulePackExecutor> acceptableRulePackExecutors;

    private CoreDispatchRulePackExecutor tester;

    @BeforeEach
    void setUp() {
        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        acceptableRulePackExecutors = new ArrayList<>();

        tester = new CoreDispatchRulePackExecutor(ruleConfigGateway, acceptableRulePackExecutors);
    }

    @Test
    void should_throw_throw_when_rulePack_is_not_acceptable() {
        acceptableRulePackExecutors.add(new AcceptableRulePackExecutor() {
            @Override
            public boolean accept(RulePack rulePack) {
                return false;
            }

            @Override
            public boolean execute(RulePack rulePack, Map<String, Object> context) {
                return false;
            }
        });

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RulePack rulePack = Mockito.mock(RulePack.class);
            tester.execute(rulePack, Collections.emptyMap());
        });

        log.error("sss", exception);
    }
}