package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public abstract class AbstractCoreDecisionSetRuleExecutorTest {
    protected RuleGroupExtendServiceFactory ruleGroupExtendServiceFactory;
    protected RuleConfigGateway ruleConfigGateway;
    protected CoreDecisionSetRuleExecutor executor;
    protected FactorValueService factorValueService;
    protected RuleFactorTypeLoader ruleFactorTypeLoader;

    protected MockedStatic<ApplicationUtils> staticApplicationUtils;

    @BeforeEach
    protected void setUp() {
        ruleGroupExtendServiceFactory = Mockito.mock(RuleGroupExtendServiceFactory.class);
        ruleFactorTypeLoader = Mockito.mock(RuleFactorTypeLoader.class);
        for (ExpressOperationEnum expressOperation : ExpressOperationEnum.values()) {
            Mockito.when(ruleFactorTypeLoader.getComparativeOperator(
                    expressOperation.getOperator())).thenReturn(
                    ComparativeOperator.builder()
                            .operator(expressOperation.getOperator())
                            .remark(expressOperation.getRemark())
                            .expression(expressOperation.getExpression()).build()
            );
        }

        executor = new CoreDecisionSetRuleExecutor(ruleGroupExtendServiceFactory, new RuleDefConditionExpressionBuilder(ruleFactorTypeLoader));

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
