package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.core.RuleDefsExecutorFactory;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.core.runner.ql.QLExpressionRunner;
import com.jd.cho.rule.engine.domain.model.RulePack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CoreDecisionSetRuleExecutor_SideTest {
    private final CoreExpressionRunner coreExpressionRunner = new QLExpressionRunner();
    private RuleDefsExecutorFactory ruleDefsExecutorFactory;
    private RuleDefConditionExpressionBuilder ruleDefConditionExpressionBuilder;

    private CoreDecisionSetRuleExecutor tester;

    @BeforeEach
    void setUp() {
        tester = new CoreDecisionSetRuleExecutor(ruleDefsExecutorFactory, ruleDefConditionExpressionBuilder, coreExpressionRunner);
    }

    @Test
    void test_accept() {
        {
            RulePack rulePack = RulePack.builder().rulePackType(RulePackTypeEnum.DECISION_SET).build();
            boolean result = tester.accept(rulePack);
            Assertions.assertThat(result).isTrue();
        }
        {
            RulePack rulePack = RulePack.builder().rulePackType(RulePackTypeEnum.NORMAL).build();
            boolean result = tester.accept(rulePack);
            Assertions.assertThat(result).isTrue();
        }
        {
            RulePack rulePack = null;
            boolean result = tester.accept(rulePack);
            Assertions.assertThat(result).isFalse();
        }
    }

    @Test
    void test_check() {
        RulePack rulePack = null;
        Assertions.assertThatThrownBy(() -> {
            boolean result = tester.execute(rulePack, new HashMap<>());
        }).isInstanceOf(IllegalArgumentException.class);
    }
}