package com.jd.cho.rule.engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Description: Web Demo
 *
 * @author DongBoot
 */
@Controller
public class DemoController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "demo") String name) {
        return "Hello World " + name;
    }
}
