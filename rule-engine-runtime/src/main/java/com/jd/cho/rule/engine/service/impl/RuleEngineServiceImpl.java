package com.jd.cho.rule.engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.cache.ContextHolder;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.core.DispatchRulePackExecutor;
import com.jd.cho.rule.engine.service.RuleEngineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class RuleEngineServiceImpl implements RuleEngineService {
    private DispatchRulePackExecutor dispatchRulePackExecutor;
    private ApplicationContext applicationContext;

    @Override
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        log.info("execute rulePackCode:{},context:{}", rulePackCode, JSON.toJSONString(context));
        try {
            context.putIfAbsent(Dict.INNER_CONTEXT, applicationContext);
            ContextHolder.setContext(context);
            return dispatchRulePackExecutor.execute(rulePackCode, context);
        } finally {
            ContextHolder.clear();
            context.remove(Dict.INNER_CONTEXT);
        }
    }
}
