package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorGroupReq;
import com.jd.cho.rule.engine.domain.model.RuleFactorGroup;
import com.jd.cho.rule.engine.service.RuleFactorGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则因子分组控制
 *
 * @author chenhonghao12
 */
@RestController
@RequestMapping("/rule/factor/group")
public class RuleFactorGroupController {

    @Resource
    private RuleFactorGroupService ruleFactorGroupService;

    /**
     * 分组因子分组查询
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<RuleFactorGroup> list() {
        return ruleFactorGroupService.queryRuleFactorGroup();
    }

    /**
     * 分组因子分组查询
     *
     * @return list
     */
    @GetMapping(value = "/tree")
    public List<RuleFactorGroup> list(String sceneCode) {
        return ruleFactorGroupService.queryGroupBySceneCode(sceneCode);
    }


    /**
     * 规则因子分组创建
     *
     * @param ruleFactorGroupReq 请求参数
     */
    @PostMapping(value = "/create")
    public void create(@RequestBody RuleFactorGroupReq ruleFactorGroupReq) {
        ruleFactorGroupService.createRuleFactorGroup(ruleFactorGroupReq);
    }


}
