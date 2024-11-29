package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoreDecisionSetRuleExecutor_MethodTest {
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

    @DisplayName("condition(single expr): methodOne = 1")
    @Test
    void test_method_eq_const() throws Exception {
        QlExpressUtil.addFunctionOfClassMethod(new ArrayList<Method>() {{
            Method methodOne = MethodOne.class.getMethod("methodOne", Integer.class);
            this.add(methodOne);
        }});
        final String methodOne = "methodOne";
        final Integer constOne = 1;
        final RuleDef ruleDef = new RuleDef() {{
            this.setPriority(1);
            this.setRuleCondition(new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setCode(methodOne);
                    this.setRuleType(VarTypeEnum.METHOD.getCode());
                    this.setParams(new ArrayList<BasicVar>() {{
                        this.add(new BasicVar() {{
                            this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                            this.setValues(constOne);
                        }});
                    }});
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constOne);
                }});
            }});
        }};

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
    }

    public static class MethodOne {
        @ApiMethod(code = "methodOne", name = "方法一", returnType = FactorTypeEnum.NUM, values = "i")
        public Integer methodOne(Integer i) {
            return i;
        }
    }
}
