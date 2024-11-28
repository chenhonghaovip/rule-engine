package com.jd.cho.rule.engine.spi;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface CustomFunctionExtendService {

    /**
     * 获取全部的拓展方法
     *
     * @return 自定义拓展方法
     */
    List<Method> extendMethods();
}
