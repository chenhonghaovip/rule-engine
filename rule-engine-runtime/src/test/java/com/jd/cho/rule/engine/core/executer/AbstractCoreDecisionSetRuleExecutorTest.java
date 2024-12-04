package com.jd.cho.rule.engine.core.executer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.RuleDefsExecutorFactory;
import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import com.jd.cho.rule.engine.core.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.core.factor.model.RuleFactorType;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
        for (FactorTypeEnum factorType : FactorTypeEnum.values()) {
            Mockito.when(ruleFactorTypeLoader.getFactorType(factorType.getCode())).thenReturn(
                    RuleFactorType.builder().code(factorType.getCode()).desc(factorType.getDesc()).build()
            );
        }

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
