package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import com.jd.cho.rule.engine.core.executer.set.CoreDecisionSetRuleExecutor;
import com.jd.cho.rule.engine.core.executer.set.group.factory.RuleDefsExecutorFactory;
import com.jd.cho.rule.engine.core.method.CustomMethodResolver;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.core.runner.ExpressionContextFactory;
import com.jd.cho.rule.engine.core.runner.ql.QLExpressionRunner;
import com.jd.cho.rule.engine.domain.model.RulePack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;

class CoreDecisionSetRuleExecutor_SideTest {
    protected ApplicationContext applicationContext;
    protected FactorValueService factorValueService;
    protected ExpressionContextFactory expressionContextFactory;

    protected CustomMethodResolver customMethodResolver = new CustomMethodResolver();
    private CoreExpressionRunner coreExpressionRunner;
    private RuleDefsExecutorFactory ruleDefsExecutorFactory;
    private RuleDefConditionExpressionBuilder ruleDefConditionExpressionBuilder;

    private CoreDecisionSetRuleExecutor tester;

    @BeforeEach
    void setUp() {
        applicationContext = Mockito.mock(ApplicationContext.class);
        expressionContextFactory = new ExpressionContextFactory(applicationContext, factorValueService);
        coreExpressionRunner = new QLExpressionRunner(customMethodResolver, expressionContextFactory);
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