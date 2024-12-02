package com.jd.cho.rule.engine.common.protocol;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RulePackDTO implements Serializable {

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
     */
    private RulePackTypeEnum rulePackType;

    /**
     * 包内规则执行策略
     */
    private String ruleArrangeStrategy;

    /**
     * 规则包描述
     */
    private String remark;

    /**
     * 包内规则信息
     */
    private String ruleContent;
}
