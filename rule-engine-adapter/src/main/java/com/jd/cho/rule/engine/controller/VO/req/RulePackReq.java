package com.jd.cho.rule.engine.controller.VO.req;

import com.jd.cho.rule.engine.controller.VO.common.RulesBean;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RulePackReq implements Serializable {

    private Long id;

    /**
     * 规则包名称
     */
    private String rulePackName;

    /**
     * 规则包code
     */
    private String rulePackCode;

    /**
     * 规则包类型
     *
     * @see com.jd.cho.rule.engine.common.enums.RulePackTypeEnum
     */
    private String rulePackType;

    /**
     * 包内规则调度策略
     *
     * @see RuleGroupExtendService
     */
    private String ruleArrangeStrategy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 规则列表
     */
    private List<RulesBean> rules;

}
