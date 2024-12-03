package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoreDecisionSetRuleExecutor_AndTest extends AbstractCoreDecisionSetRuleExecutorTest {
    @DisplayName("condition(single expr): (1 = 1) && (2 = 2)")
    @Test
    void test_const_eq_const() {
        final Integer constOne = 1;
        final Integer constTwo = 2;
        final RuleDef ruleDef = new RuleDef() {{
            this.setPriority(1);
            RuleCondition c1 = new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constOne);
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constOne);
                }});
            }};

            RuleCondition c2 = new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constTwo);
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constTwo);
                }});
            }};

            this.setRuleCondition(new RuleCondition() {{
                this.setLogicOperation(RelationTypeEnum.AND.getCode());
                this.setChildren(Arrays.asList(c1, c2));
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
    }
}
