package com.jd.cho.rule.engine.controller;

import com.alibaba.fastjson.JSON;
import com.jd.global.context.GlobalTTLContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: Context Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/vGFvw0xpAwjtoRwn8Kig">Mybatis 使用说明</a>
 */
@RestController
@RequestMapping("/ctx")
public class ContextDemoController {

    @RequestMapping("/info")
    public String info() {
        return "requestId: " + GlobalTTLContext.getGlobalRequestId() + ", context info: " + JSON.toJSONString(GlobalTTLContext.getBasicGlobalContext());
    }

}
