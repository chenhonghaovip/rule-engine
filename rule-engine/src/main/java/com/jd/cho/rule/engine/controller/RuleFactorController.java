package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.common.convert.RulePackConvert;
import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.controller.VO.req.RulePackReq;
import com.jd.cho.rule.engine.service.RuleFactorService;
import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 规则引擎规则场景控制中心
 *
 * @author chenhonghao12
 */
@RestController
@RequestMapping("/rule/factor")
public class RuleFactorController {

    @Resource
    private RuleFactorService ruleFactorService;

    @Resource
    private RulePackService rulePackService;

    /**
     * 获取规则因子列表
     *
     * @param context 场景code
     * @return list
     */
    @PostMapping(value = "/list")
    public List<RuleFactorQueryDTO> list(@RequestBody Map<String, Object> context) {
        return ruleFactorService.queryBySceneCode(context);
    }

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


    /**
     * 创建规则因子
     *
     * @param ruleFactorReq ruleFactorReq
     */
    @PostMapping(value = "/create")
    public void create(@RequestBody RuleFactorReq ruleFactorReq) {
    }


    /**
     * 修改规则因子
     *
     * @param ruleFactorReq ruleFactorReq
     */
    @PostMapping(value = "/modify")
    public void modify(@RequestBody RuleFactorReq ruleFactorReq) {
    }


}
