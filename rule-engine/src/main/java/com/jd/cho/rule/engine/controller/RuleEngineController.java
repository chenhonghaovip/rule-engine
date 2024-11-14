package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.service.RuleEngineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/engine")
public class RuleEngineController {

    @Resource
    private RuleEngineService ruleEngineService;

    /**
     * 获取因子类型列表
     *
     * @param context 请求参数
     */
    @PostMapping("/run")
    public Object execute(@RequestBody Map<String, Object> context) {
        String rulePackCode = (String) context.get("rulePackCode");
        context.put("b", new Date());
        ruleEngineService.execute(rulePackCode, context);
        return context.get("result");
    }


}
