package com.jd.cho.rule.engine.manager.impl;

import com.jd.component.donglog.log.BizLogger;
import com.jd.component.donglog.log.DongLogger;
import com.jd.cho.rule.engine.manager.LogDemoService;
import org.springframework.stereotype.Service;

/**
 * Description: DongLog Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/6srsQKdVjfibE4jODsC4">DongLog使用说明</a>
 * @see <a href="https://taishan.jd.com/DongLog/index">DongLog管理端</a>
 */
@Service
public class LogDemoServiceImpl implements LogDemoService {

    /**
     * 构造普通日志logger
     */
    private static final DongLogger LOGGER = DongLogger.getLogger(LogDemoServiceImpl.class.getName());


    /**
     * 构造业务日志logger
     */
    private static final BizLogger BIZ_LOGGER = DongLogger.getBizLogger();


    /**
     * 打印普通日志
     *
     * @see <a href="https://joyspace.jd.com/pages/W4KogJ3y8k5euhsB2OfW">动态调整日志级别</a>
     */
    @Override
    public void printLog() {

        // 打印内容：输出格式遵循系统日志规范，可在DongLog管控台配置
        // |      日期           |级别|        pfinder-traceid         | PID  | 线程 |   类名   |        日志内容       ｜
        // 24-05-08.16:39:03.145|INFO |3860007.58471.17127465662303418|64588|[main]| donglog.Demo : ------info log------

        LOGGER.info("---------------info log-------------");
    }

    /**
     * 打印业务日志
     */
    @Override
    public void printBizLog() {
        BIZ_LOGGER.info("下单确认",     // 业务场景
                100,          // 执行耗时
                true,         // 执行结果（成功或失败）
                200,          // 业务异常码
                "微信小程序");  // 自定义描述信息
    }

}
