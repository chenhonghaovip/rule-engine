package com.jd.cho.rule.engine.group;


import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
public class RuleGroupRunStrategy {
    private static final Map<String, RuleGroupExtendService> SERVICE_HASH_MAP = Maps.newHashMap();

    static {
        ServiceLoader<RuleGroupExtendService> load = ServiceLoader.load(RuleGroupExtendService.class);
        for (RuleGroupExtendService abstractRuleGroup : load) {
            String code = abstractRuleGroup.getCode();
            if (SERVICE_HASH_MAP.containsKey(code)) {
                log.error("规则组code{}已经存在，请修改避免重复", code);
                throw new BusinessException(BizErrorEnum.RULE_GROUP_CODE_IS_EXIST);
            }
            SERVICE_HASH_MAP.put(code, abstractRuleGroup);
        }
    }

    /**
     * 获取全部拓展信息
     *
     * @return 拓展接口
     */
    public static List<CommonDict> allRuleGroup() {
        return SERVICE_HASH_MAP.entrySet().stream().map(entry -> new CommonDict(entry.getKey(), entry.getValue().getName())).collect(Collectors.toList());
    }

    /**
     * 获取规则调度逻辑实体
     *
     * @param code 调度code
     * @return RuleGroupExtendService
     */
    public static RuleGroupExtendService getRuleGroup(String code) {
        return SERVICE_HASH_MAP.get(code);
    }
}
