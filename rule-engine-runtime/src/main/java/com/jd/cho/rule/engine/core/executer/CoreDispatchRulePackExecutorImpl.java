package com.jd.cho.rule.engine.core.executer;

import com.jd.cho.rule.engine.core.AcceptableRulePackExecutor;
import com.jd.cho.rule.engine.core.DispatchRulePackExecutor;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RulePack;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class CoreDispatchRulePackExecutorImpl implements DispatchRulePackExecutor {
    private RuleConfigGateway ruleConfigGateway;
    private List<AcceptableRulePackExecutor> acceptableRulePackExecutors;

    @Override
    public boolean execute(RulePack rulePack, Map<String, Object> context) {
        Assert.notNull(rulePack, "rulePack is null");
        for (AcceptableRulePackExecutor rulePackExecutor : acceptableRulePackExecutors) {
            if (rulePackExecutor.accept(rulePack)) {
                return rulePackExecutor.execute(rulePack, context);
            }
        }

        throw new IllegalArgumentException("dose not support rule pack executor:" + rulePack.getRulePackType());
    }

    @Override
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        RulePack rulePack = ruleConfigGateway.rulePackInfo(rulePackCode);
        Assert.notNull(rulePack, "rulePack is null:" + rulePackCode);

        return this.execute(rulePack, context);
    }
}
