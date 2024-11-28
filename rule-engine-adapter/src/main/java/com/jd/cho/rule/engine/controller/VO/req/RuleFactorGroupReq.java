package com.jd.cho.rule.engine.controller.VO.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleFactorGroupReq implements Serializable {

    /**
     * 规则组code
     */
    private String groupCode;

    /**
     * 规则组名称
     */
    private String groupName;

}
