package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.controller.VO.req.RuleSceneReq;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
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
@RequestMapping("/rule/scene")
public class RuleSceneController {

    @Resource
    private RuleFactorService ruleFactorService;

    /**
     * 获取规则场景列表信息
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<RuleSceneReq> list() {

        return null;
    }

    /**
     * 获取规则场景下的规则因子信息
     *
     * @param sceneCode 场景code
     * @return list
     */
    @GetMapping(value = "/factors")
    public List<RuleFactor> factors(@RequestParam("sceneCode") String sceneCode) {
        List<RuleFactorQueryDTO> ruleFactorQueryDTOS = ruleFactorService.queryBySceneCode(sceneCode);
        return null;
    }

    /**
     * 规则场景创建
     *
     * @param req req
     */
    @PostMapping(value = "/create")
    public void create(RuleSceneReq req) {
    }

    /**
     * 规则场景修改
     *
     * @param req req
     */
    @PostMapping(value = "/modify")
    public void modify(RuleSceneReq req) {
    }

}
