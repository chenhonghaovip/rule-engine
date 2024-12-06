package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.adapter.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.adapter.dto.RuleFactorDTO;
import com.jd.cho.rule.engine.adapter.dto.RuleFactorQueryDTO;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.service.RuleFactorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取规则因子列表
     *
     * @param context 场景code、规则因子常量值所需参数等
     * @return list
     */
    @PostMapping(value = "/list")
    public List<RuleFactorQueryDTO> list(@RequestBody Map<String, Object> context) {
        return ruleFactorService.queryBySceneCode(context);
    }


    /**
     * 获取规则因子的下拉列表值
     *
     * @param context 场景code、规则因子常量值所需参数等
     * @return list
     */
    @PostMapping(value = "/values")
    public List<CommonDict> factorConstantValues(@RequestBody Map<String, Object> context) {
        return ruleFactorService.factorConstantValues(context);
    }


    /**
     * 创建规则因子
     *
     * @param ruleFactorReq ruleFactorReq
     */
    @PostMapping(value = "/create")
    public String create(@RequestBody RuleFactorReq ruleFactorReq) {
        RuleFactorDTO ruleFactorEditDTO = RuleFactorConvert.INSTANCE.doToDTO(ruleFactorReq);
        return ruleFactorService.createRuleFactor(ruleFactorEditDTO);
    }


    /**
     * 修改规则因子
     *
     * @param ruleFactorReq ruleFactorReq
     */
    @PostMapping(value = "/modify")
    public void modify(@RequestBody RuleFactorReq ruleFactorReq) {
        RuleFactorDTO ruleFactorEditDTO = RuleFactorConvert.INSTANCE.doToDTO(ruleFactorReq);
        ruleFactorService.updateRuleFactor(ruleFactorEditDTO);
    }


}
