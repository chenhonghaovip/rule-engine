package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.common.convert.RulePackConvert;
import com.jd.cho.rule.engine.controller.VO.req.RulePackReq;
import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/def")
public class RuleCoreController {

    @Resource
    private RulePackService rulePackService;

    /**
     * 规则创建
     *
     * @param rulePackReq 入参信息
     * @return 规则code
     */
    @PostMapping(value = "/createRule")
    public String createRule(@RequestBody RulePackReq rulePackReq) {
        RulePackDTO rulePackDTO = RulePackConvert.INSTANCE.doToDTO(rulePackReq);
        return rulePackService.createRule(rulePackDTO);
    }

    /**
     * 规则编辑
     *
     * @param rulePackReq 入参信息
     */
    @PostMapping(value = "/updateRule")
    public void updateRule(@RequestBody RulePackReq rulePackReq) {
        RulePackDTO rulePackDTO = RulePackConvert.INSTANCE.doToDTO(rulePackReq);
        rulePackService.updateRule(rulePackDTO);
    }

    /**
     * 当前线上规则配置信息
     *
     * @param rulePackCode 规则code
     * @return 规则配置信息
     */
    @GetMapping(value = "/onlineRulePack")
    public RulePackDTO onlineRulePack(@RequestParam("rulePackCode") String rulePackCode) {
        return rulePackService.onlineRulePack(rulePackCode);
    }


    /**
     * 当前规则历史配置信息
     *
     * @param rulePackCode 规则code
     * @return 规则配置信息
     */
    @GetMapping(value = "/rulePackHistory")
    public List<RulePackDTO> rulePackHistory(@RequestParam("rulePackCode") String rulePackCode) {
        return rulePackService.rulePackHistory(rulePackCode);
    }
}
