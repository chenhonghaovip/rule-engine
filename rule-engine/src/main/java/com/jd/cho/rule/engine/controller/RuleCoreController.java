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

    @PostMapping(value = "/createRule")
    public String createRule(@RequestBody RulePackReq rulePackReq) {
        RulePackDTO rulePackDTO = RulePackConvert.INSTANCE.doToDTO(rulePackReq);
        return rulePackService.createRule(rulePackDTO);
    }

    @PostMapping(value = "/updateRule")
    public void updateRule(@RequestBody RulePackReq rulePackReq) {
        RulePackDTO rulePackDTO = RulePackConvert.INSTANCE.doToDTO(rulePackReq);
        rulePackService.updateRule(rulePackDTO);
    }

    @GetMapping(value = "/onlineRulePack")
    public RulePackDTO onlineRulePack(@RequestParam("rulePackCode") String rulePackCode) {
        return rulePackService.onlineRulePack(rulePackCode);
    }

    @GetMapping(value = "/rulePackHistory")
    public List<RulePackDTO> rulePackHistory(@RequestParam("rulePackCode") String rulePackCode) {
        return rulePackService.rulePackHistory(rulePackCode);
    }
}
