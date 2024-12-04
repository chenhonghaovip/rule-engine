package com.jd.cho.rule.engine.core.executer;


import com.jd.cho.rule.engine.core.RuleDefExecutor;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.spi.RuleDefsExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 按照优先级执行，执行到第一个规则匹配成功，执行后就返回，不再继续执行
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class PriorityOrderMatchRuleDefsExecutor implements RuleDefsExecutor {
    public static final String CODE = "OrderMatch";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getName() {
        return "当有规则被命中时终止";
    }

    @Override
    public boolean execute(RuleDefExecutor ruleDefExecutor, List<RuleDef> list, Map<String, Object> context) {
        for (RuleDef ruleDef : list) {
            if (ruleDefExecutor.execute(ruleDef, context)) {
                return true;
            }
        }
        return false;
    }
}
