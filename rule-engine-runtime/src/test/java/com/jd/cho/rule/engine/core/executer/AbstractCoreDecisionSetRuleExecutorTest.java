package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

abstract class AbstractCoreDecisionSetRuleExecutorTest {
    protected RuleGroupExtendServiceFactory ruleGroupExtendServiceFactory;
    protected RuleConfigGateway ruleConfigGateway;
    protected CoreDecisionSetRuleExecutor executor;
    protected FactorValueService factorValueService;
    protected MockedStatic<ApplicationUtils> staticApplicationUtils;

    @BeforeEach
    protected void setUp() {
        ruleGroupExtendServiceFactory = Mockito.mock(RuleGroupExtendServiceFactory.class);
        executor = new CoreDecisionSetRuleExecutor(ruleGroupExtendServiceFactory, new RuleDefConditionExpressionBuilder());

        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        factorValueService = new FactorValueServiceImpl(ruleConfigGateway);

        staticApplicationUtils = Mockito.mockStatic(ApplicationUtils.class);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(FactorValueService.class)).thenReturn(factorValueService);
    }

    @AfterEach
    protected void tearDown() {
        staticApplicationUtils.close();
    }
}
