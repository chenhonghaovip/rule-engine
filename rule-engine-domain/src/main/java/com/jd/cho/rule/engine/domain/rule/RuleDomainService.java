package com.jd.cho.rule.engine.domain.rule;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleDomainService {

    /**
     * 获取规则场景信息
     *
     * @return 规则列表信息
     */
    List<String> allScenes();

    /**
     * 添加规则场景
     *
     * @return 规则场景id
     */
    long addScene();

}
