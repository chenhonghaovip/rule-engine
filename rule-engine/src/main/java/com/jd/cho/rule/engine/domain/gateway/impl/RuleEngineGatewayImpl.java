package com.jd.cho.rule.engine.domain.gateway.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.cache.ContextHolder;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.protocol.RuleDefExpressionParser;
import com.jd.cho.rule.engine.common.protocol.RuleDefExpressionParserTest;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.domain.model.RuleAction;
import com.jd.cho.rule.engine.domain.model.RuleConditionTest;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.group.RuleGroupRunStrategy;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
public class RuleEngineGatewayImpl implements RuleEngineGateway {

    @Resource
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
        List<RuleDef> rules = rulePack.getRules();
        RuleGroupExtendService ruleGroup = RuleGroupRunStrategy.getRuleGroup(rulePack.getRuleArrangeStrategy());
        return ruleGroup.execute(rules, context);
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
        log.info("current express:{},context:{}", expression, JSON.toJSONString(context));
        boolean b = executeConditionTest(context, fieldMapping);
        System.out.println(b);
        return (Boolean) QlExpressUtil.execute(expression, context, fieldMapping);
    }

    /**
     * 执行条件
     *
     * @param context 上下文
     * @return 执行结果
     */
    private boolean executeConditionTest(Map<String, Object> context, Map<String, String> fieldMapping) {
        //language=JSON
        String s = "{\n" +
                "    \"children\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"children\":\n" +
                "            [\n" +
                "                {\n" +
                "                    \"leftVar\":\n" +
                "                    {\n" +
                "                        \"ruleType\": \"METHOD\",\n" +
                "                        \"code\": \"getInfo\",\n" +
                "                        \"originalFactorCode\": \"\",\n" +
                "                        \"params\":\n" +
                "                        [\n" +
                "                            {\n" +
                "                                \"ruleType\": \"METHOD\",\n" +
                "                                \"code\": \"getInfo\",\n" +
                "                                \"originalFactorCode\": \"\",\n" +
                "                                \"params\":\n" +
                "                                [\n" +
                "                                    {\n" +
                "                                        \"ruleType\": \"CONSTANT\",\n" +
                "                                        \"values\": \"111\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"ruleType\": \"CONSTANT\",\n" +
                "                                        \"values\": \"222\"\n" +
                "                                    }\n" +
                "                                ]\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"ruleType\": \"CONSTANT\",\n" +
                "                                \"values\": \"123\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"rightVar\":\n" +
                "                    {\n" +
                "                        \"ruleType\": \"CONSTANT\",\n" +
                "                        \"values\": \"4564\"\n" +
                "                    },\n" +
                "                    \"compareOperation\": \"TEXT_EQUAL\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"leftVar\":\n" +
                "                    {\n" +
                "                        \"ruleType\": \"FACTOR\",\n" +
                "                        \"code\": \"d\",\n" +
                "                        \"originalFactorCode\": \"d\"\n" +
                "                    },\n" +
                "                    \"rightVar\":\n" +
                "                    {},\n" +
                "                    \"compareOperation\": \"DATE_IS_NULL\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"logicOperation\": \"or\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"compareOperation\": \"COLLECTION_CONTAIN_ANY_ONE\",\n" +
                "            \"leftVar\":\n" +
                "            {\n" +
                "                \"ruleType\": \"FACTOR\",\n" +
                "                \"code\": \"a\",\n" +
                "                \"originalFactorCode\": \"a\"\n" +
                "            },\n" +
                "            \"rightVar\":\n" +
                "            {\n" +
                "                \"ruleType\": \"CONSTANT\",\n" +
                "                \"values\":\n" +
                "                [\n" +
                "                    11,\n" +
                "                    23\n" +
                "                ]\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"compareOperation\": \"DATE_AFTER\",\n" +
                "            \"leftVar\":\n" +
                "            {\n" +
                "                \"ruleType\": \"FACTOR\",\n" +
                "                \"code\": \"b\",\n" +
                "                \"originalFactorCode\": \"b\"\n" +
                "            },\n" +
                "            \"rightVar\":\n" +
                "            {\n" +
                "                \"ruleType\": \"CONSTANT\",\n" +
                "                \"values\": 1731551168000\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"logicOperation\": \"and\"\n" +
                "}";
        RuleConditionTest ruleConditionTest = JSON.parseObject(s, RuleConditionTest.class);
        Map<String, String> objectObjectHashMap = Maps.newHashMap();
        String expression1 = RuleDefExpressionParserTest.buildWhenExpression(ruleConditionTest, objectObjectHashMap);
        System.out.println(expression1);
        return (Boolean) QlExpressUtil.execute(expression1, context, fieldMapping);
    }


    /**
     * 执行动作
     *
     * @param ruleActionBeans 动作集合
     * @param context         上下文
     */
    public void executeAction(List<RuleAction> ruleActionBeans, Map<String, Object> context) {
        log.info("rule engine condition is true,execute action:{}", JSON.toJSONString(ruleActionBeans));
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
