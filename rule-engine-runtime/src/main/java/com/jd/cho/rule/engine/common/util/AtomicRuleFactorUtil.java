package com.jd.cho.rule.engine.common.util;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.spi.RuleFactorExtendService;

import java.util.List;
import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class AtomicRuleFactorUtil {
    private static final List<RuleFactorExtendService> EXTEND_SERVICES = Lists.newArrayList();

    static {
        ServiceLoader<RuleFactorExtendService> load = ServiceLoader.load(RuleFactorExtendService.class);
        for (RuleFactorExtendService ruleFactorExtendService : load) {
            EXTEND_SERVICES.add(ruleFactorExtendService);
        }
    }

    /**
     * 扩展因子
     *
     * @param ruleFactorList 原始因子列表
     * @return 扩展因子列表
     */
    public static List<RuleFactor> extendFactors(List<RuleFactor> ruleFactorList) {
        for (RuleFactorExtendService extendService : EXTEND_SERVICES) {
            ruleFactorList = extendService.extendFactors(ruleFactorList);
        }
        return ruleFactorList;
    }


}
