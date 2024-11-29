package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.atomic.impl.FactorValueServiceImpl;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
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

public class CoreDecisionSetRuleExecutor_FactorTest {
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

    @DisplayName("condition(single expr): factorOne = 1")
    @Test
    void test_factor_eq_const() {
        final String factorOne = "factorOne";
        final Integer constOne = 1;
        final RuleDef ruleDef = new RuleDef() {{
            this.setPriority(1);
            this.setRuleCondition(new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_EQUAL.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.FACTOR.getCode());
                    this.setCode(factorOne + "Code");
                    this.setOriginalFactorCode(factorOne);
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.CONSTANT.getCode());
                    this.setValues(constOne);
                }});
            }});
        }};

        Mockito.when(ruleConfigGateway.queryFactorCodes()).thenReturn(new ArrayList<RuleFactor>() {{
            this.add(RuleFactor.builder().factorCode(factorOne).factorScript("return 1;").build());
        }});

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
    }

    @DisplayName("condition(single expr): factorOne < factorTwo")
    @Test
    void test_factor_eq_factor() {
        final String factorOne = "factorOne";
        final String factorTwo = "factorTwo";
        final RuleDef ruleDef = new RuleDef() {{
            this.setPriority(1);
            this.setRuleCondition(new RuleCondition() {{
                this.setCompareOperation(ExpressOperationEnum.NUM_LESS_THAN.getOperator());
                this.setLeftVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.FACTOR.getCode());
                    this.setCode(factorOne + "Code");
                    this.setOriginalFactorCode(factorOne);
                }});
                this.setRightVar(new BasicVar() {{
                    this.setRuleType(VarTypeEnum.FACTOR.getCode());
                    this.setCode(factorTwo + "Code");
                    this.setOriginalFactorCode(factorTwo);
                }});
            }});
        }};

        Mockito.when(ruleConfigGateway.queryFactorCodes()).thenReturn(new ArrayList<RuleFactor>() {{
            this.add(RuleFactor.builder().factorCode(factorOne).factorScript("return 1;").build());
            this.add(RuleFactor.builder().factorCode(factorTwo).factorScript("return 2;").build());
        }});

        final Map<String, Object> context = new HashMap<>();
        boolean matched = executor.execute(ruleDef, context);
        Assertions.assertThat(matched).isTrue();
    }
}
