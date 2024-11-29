package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.ActionType;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleAction;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CoreDecisionSetRuleExecutorTest {
    private RuleConfigGateway ruleConfigGateway;
    private CoreDecisionSetRuleExecutor executor;
    private FactorValueService factorValueService;
    private MockedStatic<ApplicationUtils> staticApplicationUtils;

    @BeforeEach
    void setUp() {
        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        executor = new CoreDecisionSetRuleExecutor(ruleConfigGateway);
        factorValueService = new FactorValueServiceImpl(ruleConfigGateway);
        staticApplicationUtils = Mockito.mockStatic(ApplicationUtils.class);
        staticApplicationUtils.when(() -> ApplicationUtils.getBeans(FactorValueService.class)).thenReturn(factorValueService);
    }

    @AfterEach
    void tearDown() {
        staticApplicationUtils.close();
    }

    @DisplayName("condition(single expr): 1 = 1")
    @Test
    void test_const_eq_const() {
        final Integer constValue = 1;
        final RuleDef ruleDef = new RuleDef() {{
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

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
    }

    @DisplayName("condition(single expr): 1 = 1, action=assign('resultNum',10)")
    @Test
    void test_const_eq_const_and_setResult() {
        final Integer constValue = 1;
        final RuleDef ruleDef = new RuleDef() {{
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
            this.setRuleActions(new ArrayList<RuleAction>() {{
                this.add(new RuleAction() {{
                    this.setExecuteType(ActionType.ASSIGN.getCode());
                    this.setFieldCode("resultNum");
                    this.setValues(10);
                }});
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
        Map<String, Object> result = (Map<String, Object>) context.get("result");
        Assertions.assertThat(result).containsEntry("resultNum", 10);
    }
}