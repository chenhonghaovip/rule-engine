package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ActionType;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleAction;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CoreDecisionSetRuleExecutor_ConstTest extends AbstractCoreDecisionSetRuleExecutorTest {

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

    @DisplayName("condition(single expr): not 1 = 2")
    @Test
    void test_not_const_eq_const() {
        final Integer constOne = 1;
        final Integer constTwo = 2;
        final RuleDef ruleDef = new RuleDef() {{
            this.setPriority(1);
            this.setRuleCondition(new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constOne);
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constTwo);
                }});
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isFalse();
    }

    @DisplayName("condition(single expr): 1 = 1, action=assign('resultNum',10)")
    @Test
    void test_const_eq_const_and_setResult() {
        final Integer constValue = 1;
        final String resultNumKey = "resultNum";
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
                    this.setFieldCode(resultNumKey);
                    this.setValues(10);
                }});
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
        Map<String, Object> result = (Map<String, Object>) context.get(Dict.RESULT_ALIAS);
        Assertions.assertThat(result).containsEntry(resultNumKey, 10);
    }

    @DisplayName("condition(single expr): 1 = 1, initResult, action=assign('resultNum',10)")
    @Test
    void test_const_eq_const_and_initResult_setResult() {
        final Integer constValue = 1;
        final String resultNumKey = "resultNum";
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
                    this.setFieldCode(resultNumKey);
                    this.setValues(10);
                }});
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        context.put(Dict.RESULT_ALIAS, new HashMap<String, Object>() {{
            this.put("a", "A");
        }});
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
        Map<String, Object> result = (Map<String, Object>) context.get(Dict.RESULT_ALIAS);
        Assertions.assertThat(result).containsEntry(resultNumKey, 10);
        Assertions.assertThat(result).containsEntry("a", "A");
    }
}