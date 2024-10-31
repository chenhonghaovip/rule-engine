package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.manager.DuccDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: DUCC Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/s8A75GLGvGiFbRA4ufZa">DUCC使用说明</a>
 * @see <a href="https://taishan.jd.com/ducc/web/nswork">DUCC管理端</a>
 */
@RestController
@RequestMapping("/ducc")
public class DuccDemoController {

    @Resource
    private DuccDemoService duccDemoService;

    /**
     * Ducc 测试
     *
     * <a href="http://localhost:8080/ducc/get">get</a>
     *
     */
    @RequestMapping("/get")
    public String get() {
        return "get from ducc success , value is: " + duccDemoService.getMessage();
    }

}
