package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

class CoreDecisionSetRuleExecutorTest {

    private RuleConfigGateway ruleConfigGateway;
    private CoreDecisionSetRuleExecutor executor;

    @BeforeEach
    void setUp() {
        ruleConfigGateway = Mockito.mock(RuleConfigGateway.class);
        executor = new CoreDecisionSetRuleExecutor(ruleConfigGateway);
    }

    @DisplayName("condition: const eq const")
    @Test
    void test_const_eq_const() {
//        final String factorCode = "factorX";
//        final String originalFactorCode = "originalFactorX";
        final Integer constValue = 1;

        final RuleDef ruleDef = new RuleDef();
        ruleDef.setPriority(1);
        ruleDef.setRuleCondition(new RuleCondition() {{
            this.setCompareOperation(ExpressOperationEnum.TEXT_EQUAL.getOperator());
            this.setLeftVar(new BasicVar() {{
                this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                this.setValues(constValue);
            }});
            this.setRightVar(new BasicVar() {{
                this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                this.setValues(constValue);
            }});
        }});

        final Map<String, Object> context = new HashMap<>();
        boolean result = executor.execute(ruleDef, context);

        Assertions.assertThat(result).isTrue();
    }
}