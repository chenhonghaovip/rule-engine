package com.jd.cho.rule.engine.core.executer.set.group.extend;

import com.jd.cho.rule.engine.core.executer.set.group.RuleDefExecutor;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.spi.RuleDefsExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 按照优先级顺序执行，优先级高的先执行，直到执行完全部规则
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class PriorityOrderSeqRuleDefsExecutor implements RuleDefsExecutor {

    @Override
    public String getCode() {
        return "OrderSeq";
    }

    @Override
    public String getName() {
        return "顺序执行所有规则";
    }

    @Override
    public boolean execute(RuleDefExecutor ruleDefExecutor, List<RuleDef> list, Map<String, Object> context) {
        for (RuleDef ruleDef : list) {
            ruleDefExecutor.execute(ruleDef, context);
        }
        return true;
    }
}
