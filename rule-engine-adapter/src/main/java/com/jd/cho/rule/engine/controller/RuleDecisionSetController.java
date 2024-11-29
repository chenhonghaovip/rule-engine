package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.adapter.convert.RulePackConvert;
import com.jd.cho.rule.engine.controller.VO.req.RulePackReq;
import com.jd.cho.rule.engine.controller.VO.resp.RulePackResp;
import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/decision_set/def")
public class RuleDecisionSetController {

    @Resource
    private RulePackService rulePackService;

    /**
     * 规则集合创建
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
     * 规则集合编辑
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
    public RulePackResp onlineRulePack(@RequestParam("rulePackCode") String rulePackCode) {
        RulePackDTO rulePackDTO = rulePackService.onlineRulePack(rulePackCode);
        return RulePackConvert.INSTANCE.doToResp(rulePackDTO);
    }


    /**
     * 当前规则历史配置信息
     *
     * @param rulePackCode 规则code
     * @return 规则配置信息
     */
    @GetMapping(value = "/rulePackHistory")
    public List<RulePackResp> rulePackHistory(@RequestParam("rulePackCode") String rulePackCode) {
        List<RulePackDTO> rulePackList = rulePackService.rulePackHistory(rulePackCode);
        return rulePackList.stream().map(RulePackConvert.INSTANCE::doToResp).collect(Collectors.toList());
    }
}
