package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.common.convert.RuleSceneConvert;
import com.jd.cho.rule.engine.controller.VO.req.RuleSceneReq;
import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.service.RuleSceneService;
import com.jd.cho.rule.engine.service.dto.RuleSceneDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RuleSceneService ruleSceneService;

    /**
     * 获取规则场景列表信息
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<RuleSceneResp> list() {
        return ruleSceneService.queryRuleScene();
    }


    /**
     * 规则场景创建
     *
     * @param req req
     */
    @PostMapping(value = "/create")
    public String create(RuleSceneReq req) {
        RuleSceneDTO ruleSceneDTO = RuleSceneConvert.INSTANCE.doToDTO(req);
        return ruleSceneService.createRuleScene(ruleSceneDTO);
    }

    /**
     * 规则场景修改
     *
     * @param req req
     */
    @PostMapping(value = "/modify")
    public void modify(RuleSceneReq req) {
        RuleSceneDTO ruleSceneDTO = RuleSceneConvert.INSTANCE.doToDTO(req);
        ruleSceneService.updateRuleScene(ruleSceneDTO);
    }

}
