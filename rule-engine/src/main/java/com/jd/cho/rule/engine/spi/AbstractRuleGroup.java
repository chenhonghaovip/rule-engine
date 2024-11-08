package com.jd.cho.rule.engine.spi;


import com.jd.cho.rule.engine.domain.model.RuleDef;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public abstract class AbstractRuleGroup {

    /**
     * 执行规则组
     *
     * @param list    规则定义信息
     * @param context 规则上下文
     * @return 是否执行成功
     */
    public abstract boolean execute(List<RuleDef> list, Map<String, Object> context);


    public abstract String getCode();

}
