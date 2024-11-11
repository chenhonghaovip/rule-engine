package com.jd.cho.rule.engine.controller.VO.resp;

import lombok.Data;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleSceneResp {


    private Long id;
    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 场景描述
     */
    private String sceneDesc;

    /**
     * 分组信息
     */
    private List<RuleFactorGroupResp> ruleFactorGroups;


    @Data
    public static class RuleFactorGroupResp {
        private Long id;
        /**
         * 分组编码
         */
        private String groupCode;

        /**
         * 分组名称
         */
        private String groupName;
    }
}
