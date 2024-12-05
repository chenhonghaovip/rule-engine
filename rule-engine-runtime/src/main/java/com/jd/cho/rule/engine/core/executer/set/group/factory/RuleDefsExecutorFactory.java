package com.jd.cho.rule.engine.core.executer.set.group.factory;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.spi.RuleDefsExecutor;

import java.util.List;

/**
 * @author chenhonghao12
 */
public interface RuleDefsExecutorFactory {
    RuleDefsExecutor get(String ruleArrangeStrategy);

    /**
     * 获取全部拓展信息
     *
     * @return 拓展接口
     */
    List<CommonDict> allRuleGroup();
}
