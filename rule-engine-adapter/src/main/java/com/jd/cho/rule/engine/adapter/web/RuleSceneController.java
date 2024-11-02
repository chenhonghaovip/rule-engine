package com.jd.cho.rule.engine.adapter.web;

import com.jd.cho.rule.engine.client.api.CustomerServiceI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 规则引擎规则场景控制中心
 *
 * @author chenhonghao12
 */
@RestController
@RequestMapping("/rule/scene")
public class RuleSceneController {

    @Resource
    private CustomerServiceI customerService;

    @GetMapping(value = "/helloworld")
    public String helloWorld() {
        return "Hello, welcome to COLA world!";
    }

    /**
     * 获取规则场景列表信息
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public String getCustomer() {
        return null;
    }


}
