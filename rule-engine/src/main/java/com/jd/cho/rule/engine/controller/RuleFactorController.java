package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.service.RuleFactorService;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;
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
    public List<RuleFactorQueryDTO> list(@RequestParam("groupCode") String groupCode) {

        return null;
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
