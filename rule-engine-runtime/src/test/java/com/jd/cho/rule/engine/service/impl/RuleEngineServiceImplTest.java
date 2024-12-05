package com.jd.cho.rule.engine.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.protocol.RuleDefConditionExpressionBuilder;
import com.jd.cho.rule.engine.core.dispatch.CoreDispatchRulePackExecutor;
import com.jd.cho.rule.engine.core.executer.AbstractCoreDecisionSetRuleExecutorTest;
import com.jd.cho.rule.engine.core.executer.AcceptableRulePackExecutor;
import com.jd.cho.rule.engine.core.executer.set.CoreDecisionSetRuleExecutor;
import com.jd.cho.rule.engine.core.executer.set.group.extend.PriorityOrderMatchRuleDefsExecutor;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.RuleEngineService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RuleEngineServiceImplTest extends AbstractCoreDecisionSetRuleExecutorTest {
    protected List<AcceptableRulePackExecutor> acceptableRulePackExecutors;
    protected CoreDispatchRulePackExecutor dispatchRulePackExecutor;
    protected ApplicationContext applicationContext;

    protected RuleEngineService tester;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        acceptableRulePackExecutors = new ArrayList<AcceptableRulePackExecutor>() {{
            this.add(new CoreDecisionSetRuleExecutor(ruleDefsExecutorFactory, new RuleDefConditionExpressionBuilder(ruleFactorTypeLoader), coreExpressionRunner));
        }};

        applicationContext = Mockito.mock(ApplicationContext.class);
        dispatchRulePackExecutor = new CoreDispatchRulePackExecutor(ruleConfigGateway, acceptableRulePackExecutors);

        tester = new RuleEngineServiceImpl(dispatchRulePackExecutor, applicationContext);
    }

    @AfterEach
    protected void tearDown() {
        super.tearDown();
    }

    @DisplayName("condition(single expr): 1 = 1")
    @Test
    void test_const_eq_const() throws JsonProcessingException {
        final String rulePackCode = "ss";
        final Map<String, Object> context = new HashMap<>();
        final RulePack rulePack = new RulePack() {{
            this.setRulePackCode(rulePackCode);
            this.setRulePackType(RulePackTypeEnum.DECISION_SET);
            this.setRuleArrangeStrategy(PriorityOrderMatchRuleDefsExecutor.CODE);
            ArrayList<RuleDef> ruleDefs = new ArrayList<RuleDef>() {{
                final RuleDef ruleDef = new RuleDef() {{
                    final Integer constValue = 1;
                    this.setPriority(1);
                    this.setRuleCondition(new RuleCondition() {{
                        this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                        this.setLeftVar(new BasicVar() {{
                            this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                            this.setValues(constValue);
                        }});
                        this.setRightVar(new BasicVar() {{
                            this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                            this.setValues(constValue);
                        }});
                    }});
                }};
                this.add(ruleDef);
            }};
            this.setRuleContent(objectMapper.writeValueAsString(ruleDefs));
        }};

        Mockito.when(ruleConfigGateway.rulePackInfo(rulePackCode)).thenReturn(rulePack);

        boolean matched = tester.execute(rulePackCode, context);
        Assertions.assertThat(matched).isTrue();
    }
}