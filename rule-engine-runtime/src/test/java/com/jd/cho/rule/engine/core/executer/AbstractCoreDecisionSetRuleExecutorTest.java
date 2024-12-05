package com.jd.cho.rule.engine.core.executer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import com.jd.cho.rule.engine.core.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.core.executer.set.CoreDecisionSetRuleExecutor;
import com.jd.cho.rule.engine.core.executer.set.group.extend.PriorityOrderMatchRuleDefsExecutor;
import com.jd.cho.rule.engine.core.executer.set.group.extend.PriorityOrderSeqRuleDefsExecutor;
import com.jd.cho.rule.engine.core.executer.set.group.factory.RuleDefsExecutorFactory;
import com.jd.cho.rule.engine.core.executer.set.group.factory.RuleDefsExecutorFactoryImpl;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.extend.*;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.core.runner.ql.QLExpressionRunner;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

public abstract class AbstractCoreDecisionSetRuleExecutorTest {
    protected CoreExpressionRunner coreExpressionRunner = new QLExpressionRunner();
    protected RuleDefsExecutorFactory ruleDefsExecutorFactory;
    protected RuleConfigGateway ruleConfigGateway;
    protected CoreDecisionSetRuleExecutor executor;
    protected FactorValueService factorValueService;
    protected RuleFactorTypeLoader ruleFactorTypeLoader;

    protected MockedStatic<ApplicationUtils> staticApplicationUtils;

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    protected void setUp() {
        ruleDefsExecutorFactory = new RuleDefsExecutorFactoryImpl(Arrays.asList(new PriorityOrderMatchRuleDefsExecutor(), new PriorityOrderSeqRuleDefsExecutor()));
        ruleFactorTypeLoader = new RuleFactorTypeLoader(Arrays.asList(new BooleanFactorTypeService(), new DateFactorTypeService(), new ListFactorTypeService(), new NumFactorTypeService(), new TextFactorTypeService()));
        ruleFactorTypeLoader.afterPropertiesSet();

        executor = new CoreDecisionSetRuleExecutor(ruleDefsExecutorFactory,
                new RuleDefConditionExpressionBuilder(ruleFactorTypeLoader, coreExpressionRunner), coreExpressionRunner);

        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        factorValueService = new FactorValueServiceImpl(ruleConfigGateway, coreExpressionRunner);

        staticApplicationUtils = Mockito.mockStatic(ApplicationUtils.class);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(FactorValueService.class)).thenReturn(factorValueService);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(RuleFactorTypeLoader.class)).thenReturn(ruleFactorTypeLoader);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(CoreExpressionRunner.class)).thenReturn(coreExpressionRunner);
    }

    @AfterEach
    protected void tearDown() {
        staticApplicationUtils.close();
    }
}
