package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.manager.ThreadDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * Description: DongThread Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/QC6YAkxyJI9MDudZksjh">DongThread使用说明</a>
 */
@RestController
@RequestMapping("/thread")
public class ThreadDemoController {

    @Resource
    private ThreadDemoService threadDemoService;

     @RequestMapping("/get")
    public String getFromScheduledThreadPool(@RequestParam(name = "text", defaultValue = "demo") String text) throws ExecutionException, InterruptedException {
        return threadDemoService.getFromThreadPool(text) + ", " + threadDemoService.getFromScheduledThreadPool(text) + ", " +
                threadDemoService.getFromThreadPoolTask(text);
    }

}