package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.group.RuleGroupRunStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/common")
public class RuleCommonController {

    /**
     * 获取因子类型列表
     *
     * @return 因子支持类型
     */
    @GetMapping("/factor/type")
    public List<FactorTypeEnum> factorTypeEnums() {
        return FactorTypeEnum.FACTOR_TYPE_ENUMS;
    }

    /**
     * 获取包内规则调度策略
     *
     * @return 规则类型
     */
    @GetMapping("/rule/strategy")
    public List<CommonDict> ruleArrangeStrategyEnums() {
        return RuleGroupRunStrategy.allRuleGroup();
    }


    /**
     * 获取规则类型列表
     *
     * @return 规则类型
     */
    @GetMapping("/rule/type")
    public List<RulePackTypeEnum> rulePackTypeEnums() {
        return RulePackTypeEnum.RULE_PACK_TYPE_ENUMS;
    }


}
