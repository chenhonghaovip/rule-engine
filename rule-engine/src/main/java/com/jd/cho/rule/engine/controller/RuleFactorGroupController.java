package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorGroupReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 规则因子分组控制
 *
 * @author chenhonghao12
 */
@RestController
@RequestMapping("/rule/factor/group")
public class RuleFactorGroupController {

    /**
     * 分组因子分组查询
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<RuleFactorGroupReq> list() {
        return null;
    }


    /**
     * 规则因子分组创建
     *
     * @param ruleFactorGroupReq 请求参数
     */
    @PostMapping(value = "/create")
    public void create(RuleFactorGroupReq ruleFactorGroupReq) {
    }


}
