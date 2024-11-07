package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.service.RuleFactorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 获取规则场景列表信息
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public String list() {

        return null;
    }


    @PostMapping(value = "/create")
    public String create(RuleFactorReq ruleFactorReq) {
        return null;
    }


    @PostMapping(value = "/modify")
    public String modify(RuleFactorReq ruleFactorReq) {
        return null;
    }

}
