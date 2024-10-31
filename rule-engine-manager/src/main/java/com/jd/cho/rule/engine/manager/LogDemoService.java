package com.jd.cho.rule.engine.manager;

/**
 * Description: DongLog Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/6srsQKdVjfibE4jODsC4">DongLog使用说明</a>
 * @see <a href="https://taishan.jd.com/DongLog/index">DongLog管理端</a>
 */
public interface LogDemoService {

    /**
     * 打印普通日志
     *
     * @see <a href="https://joyspace.jd.com/pages/W4KogJ3y8k5euhsB2OfW">动态调整日志级别</a>
     */
    void printLog();

    /**
     * 打印业务日志
     */
    void printBizLog();

}
