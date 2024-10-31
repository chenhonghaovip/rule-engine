package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.manager.LogDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: DongLog Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/6srsQKdVjfibE4jODsC4">DongLog使用说明</a>
 * @see <a href="https://taishan.jd.com/DongLog/index">DongLog管理端</a>
 */
@RestController
@RequestMapping("/log")
public class LogDemoController {

    @Resource
    private LogDemoService logDemoService;

    /**
     * DongLog 测试
     *
     * <a href="http://localhost:8080/log/printLog">printLog</a>
     *
     * @return
     */
    @RequestMapping("/print")
    public String printLog() {
        logDemoService.printLog();
        return "print log success , please see log file";
    }

    /**
     * DongLog 测试
     *
     * <a href="http://localhost:8080/log/printBizLog">printBizLog</a>
     *
     * @return
     */
    @RequestMapping("/print-biz")
    public String printBizLog() {
        logDemoService.printBizLog();
        return "print biz log success , please see log file";
    }

}