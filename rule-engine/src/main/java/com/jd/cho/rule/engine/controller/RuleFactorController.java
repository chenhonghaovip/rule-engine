package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.service.RuleFactorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 获取规则因子列表
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<RuleFactorReq> list(@RequestParam("groupCode") String groupCode) {

        return null;
    }


    @PostMapping(value = "/create")
    public void create(RuleFactorReq ruleFactorReq) {
    }


    @PostMapping(value = "/modify")
    public void modify(RuleFactorReq ruleFactorReq) {
    }

}
