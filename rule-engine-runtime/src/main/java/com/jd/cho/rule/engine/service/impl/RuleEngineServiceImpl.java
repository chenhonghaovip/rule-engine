package com.jd.cho.rule.engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.cache.ContextHolder;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.service.RuleEngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    @Resource
    private RuleEngineGateway ruleEngineGateway;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        log.info("execute rulePackCode:{},context:{}", rulePackCode, JSON.toJSONString(context));
        try {
            context.putIfAbsent(Dict.INNER_CONTEXT, applicationContext);
            ContextHolder.setContext(context);
            return ruleEngineGateway.execute(rulePackCode, context);
        } finally {
            ContextHolder.clear();
            context.remove(Dict.INNER_CONTEXT);
        }
    }

}
