package com.jd.cho.rule.engine.core.executer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.cache.ContextHolder;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.protocol.ProtocolStrategy;
import com.jd.cho.rule.engine.common.protocol.RuleDefExpressionParser;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.core.DecisionSetRuleExecutor;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleAction;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.group.RuleGroupRunStrategy;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class CoreDecisionSetRuleExecutor implements DecisionSetRuleExecutor {

    private RuleConfigGateway ruleConfigGateway;

    @Override
    public boolean execute(RuleDef ruleDef, Map<String, Object> context) {
        Map<String, String> fieldMapping = Maps.newHashMap();
        String statement = RuleDefExpressionParser.buildWhenExpression(ruleDef.getRuleCondition(), fieldMapping);
        if (this.executeCondition(statement, context, fieldMapping)) {
            this.executeAction(ruleDef.getRuleActions(), context);
            return true;
        }
        return false;
    }

    @Override
    public boolean execute(RulePack rulePack, Map<String, Object> context) {
        if (Arrays.asList(RulePackTypeEnum.DECISION_SET, RulePackTypeEnum.NORMAL).contains(rulePack.getRulePackType())) {
            List<RuleDef> rules = JSON.parseArray(rulePack.getRuleContent(), RuleDef.class);
            RuleGroupExtendService ruleGroup = RuleGroupRunStrategy.getRuleGroup(rulePack.getRuleArrangeStrategy());
            return ruleGroup.execute(rules, context);
        }
        //todo 支持其余模式下的决策类型，目前不使用，没有开发
        String qlExpress = ProtocolStrategy.getQlExpress(rulePack);
        return true;
    }

    @Override
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        RulePack rulePack = ruleConfigGateway.rulePackInfo(rulePackCode);
        if (Objects.isNull(rulePack)) {
            return false;
        }
        return this.execute(rulePack, context);
    }

    /**
     * 执行条件
     *
     * @param expression 表达式
     * @param context    上下文
     * @return 执行结果
     */
    private boolean executeCondition(String expression, Map<String, Object> context, Map<String, String> fieldMapping) {
        log.info("current express:{}", expression);
        return (Boolean) QlExpressUtil.execute(expression, context, fieldMapping);
    }

    /**
     * 执行动作
     *
     * @param ruleActionBeans 动作集合
     * @param context         上下文
     */
    public void executeAction(List<RuleAction> ruleActionBeans, Map<String, Object> context) {
        log.info("rule engine condition is true,execute action:{}", JSON.toJSONString(ruleActionBeans));
        if (ruleActionBeans == null || ruleActionBeans.isEmpty()) {
            return;
        }

        Map<String, Object> resultMap = Maps.newHashMap();
        ruleActionBeans.forEach(each -> resultMap.put(each.getFieldCode(), each.getValues()));
        Object result = context.get(Dict.RESULT_ALIAS);
        Map<String, Object> contextResult;
        if (Objects.nonNull(result)) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            contextResult = new Gson().fromJson(JSON.toJSONString(result), type);
        } else {
            contextResult = Maps.newHashMap();

        }
        contextResult.putAll(resultMap);
        context.put(Dict.RESULT_ALIAS, contextResult);
        ContextHolder.setContext(context);
    }
}
