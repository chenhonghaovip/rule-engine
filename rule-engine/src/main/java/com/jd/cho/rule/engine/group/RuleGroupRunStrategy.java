package com.jd.cho.rule.engine.group;


import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class RuleGroupRunStrategy {
    private static Map<String, RuleGroupExtendService> ruleGroupMap = Maps.newHashMap();

    static {
        ServiceLoader<RuleGroupExtendService> load = ServiceLoader.load(RuleGroupExtendService.class);
        for (RuleGroupExtendService abstractRuleGroup : load) {
            ruleGroupMap.put(abstractRuleGroup.getCode(), abstractRuleGroup);
        }
    }

    /**
     * 获取全部拓展信息
     *
     * @return 拓展接口
     */
    public static List<CommonDict> allRuleGroup() {
        return ruleGroupMap.entrySet().stream().map(entry -> new CommonDict(entry.getKey(), entry.getValue().getName())).collect(Collectors.toList());
    }

    /**
     * 获取规则调度逻辑实体
     *
     * @param code 调度code
     * @return RuleGroupExtendService
     */
    public static RuleGroupExtendService getRuleGroup(String code) {
        return ruleGroupMap.get(code);
    }
}
