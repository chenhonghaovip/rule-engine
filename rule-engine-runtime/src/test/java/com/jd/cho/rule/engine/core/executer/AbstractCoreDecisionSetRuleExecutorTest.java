package com.jd.cho.rule.engine.core.executer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.RuleDefsExecutorFactory;
import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import com.jd.cho.rule.engine.core.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.extend.*;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

public abstract class AbstractCoreDecisionSetRuleExecutorTest {
    protected RuleDefsExecutorFactory ruleDefsExecutorFactory;
    protected RuleConfigGateway ruleConfigGateway;
    protected CoreDecisionSetRuleExecutor executor;
    protected FactorValueService factorValueService;
    protected RuleFactorTypeLoader ruleFactorTypeLoader;

    protected MockedStatic<ApplicationUtils> staticApplicationUtils;

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    protected void setUp() {
        ruleDefsExecutorFactory = Mockito.mock(RuleDefsExecutorFactory.class);
        ruleFactorTypeLoader = new RuleFactorTypeLoader(Arrays.asList(new BooleanFactorTypeService(), new DateFactorTypeService(), new ListFactorTypeService(), new NumFactorTypeService(), new TextFactorTypeService()));
        ruleFactorTypeLoader.afterPropertiesSet();

        executor = new CoreDecisionSetRuleExecutor(ruleDefsExecutorFactory, new RuleDefConditionExpressionBuilder(ruleFactorTypeLoader));

        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        factorValueService = new FactorValueServiceImpl(ruleConfigGateway);

        staticApplicationUtils = Mockito.mockStatic(ApplicationUtils.class);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(FactorValueService.class)).thenReturn(factorValueService);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(RuleFactorTypeLoader.class)).thenReturn(ruleFactorTypeLoader);
    }

    @AfterEach
    protected void tearDown() {
        staticApplicationUtils.close();
    }
}
