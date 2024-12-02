package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.core.AcceptableRulePackExecutor;
import com.jd.cho.rule.engine.core.DispatchRulePackExecutor;
import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.core.executer.CoreDecisionSetRuleExecutor;
import com.jd.cho.rule.engine.core.executer.CoreDispatchRulePackExecutorImpl;
import com.jd.cho.rule.engine.core.extend.PriorityOrderMatchRuleGroup;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RuleEngineServiceImplTest {
    protected RuleGroupExtendServiceFactory ruleGroupExtendServiceFactory;
    protected List<AcceptableRulePackExecutor> acceptableRulePackExecutors;

    protected RuleConfigGateway ruleConfigGateway;

    protected DispatchRulePackExecutor dispatchRulePackExecutor;
    protected ApplicationContext applicationContext;

    protected RuleEngineService ruleEngine;

    protected FactorValueService factorValueService;
    protected MockedStatic<ApplicationUtils> staticApplicationUtils;

    @BeforeEach
    protected void setUp() {
        ruleGroupExtendServiceFactory = Mockito.mock(RuleGroupExtendServiceFactory.class);
        acceptableRulePackExecutors = new ArrayList<AcceptableRulePackExecutor>() {{
            this.add(new CoreDecisionSetRuleExecutor(ruleGroupExtendServiceFactory));
        }};

        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        dispatchRulePackExecutor = new CoreDispatchRulePackExecutorImpl(ruleConfigGateway, acceptableRulePackExecutors);

        applicationContext = Mockito.mock(ApplicationContext.class);
        ruleEngine = new RuleEngineServiceImpl(dispatchRulePackExecutor, applicationContext);

        factorValueService = new FactorValueServiceImpl(ruleConfigGateway);

        staticApplicationUtils = Mockito.mockStatic(ApplicationUtils.class);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(FactorValueService.class)).thenReturn(factorValueService);
    }

    @AfterEach
    protected void tearDown() {
        staticApplicationUtils.close();
    }

    @DisplayName("condition(single expr): 1 = 1")
    @Test
    void test_const_eq_const() {
        final String rulePackCode = "ss";
        final Map<String, Object> context = new HashMap<>();
        final RulePack rulePack = new RulePack() {{
            this.setRulePackCode(rulePackCode);
            this.setRulePackType(RulePackTypeEnum.DECISION_SET);
            this.setRuleArrangeStrategy(PriorityOrderMatchRuleGroup.CODE);
            this.setRules(new ArrayList<RuleDef>() {{
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
            }});
        }};

        Mockito.when(ruleConfigGateway.rulePackInfo(rulePackCode)).thenReturn(rulePack);
        Mockito.when(ruleGroupExtendServiceFactory.get(PriorityOrderMatchRuleGroup.CODE)).thenReturn(new PriorityOrderMatchRuleGroup());

        boolean matched = ruleEngine.execute(rulePackCode, context);
        Assertions.assertThat(matched).isTrue();
    }
}