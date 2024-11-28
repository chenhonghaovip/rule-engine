package com.jd.cho.rule.engine.common.util;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.spi.CustomFunctionExtendService;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class AtomicCustomFunctionUtil {
    /**
     * 获取自定义方法
     *
     * @return 自定义方法信息，依赖于 SPI 机制
     */
    public static List<Method> getCustomFunction() {
        List<Method> methods = Lists.newArrayList();
        ServiceLoader<CustomFunctionExtendService> load = ServiceLoader.load(CustomFunctionExtendService.class);
        for (CustomFunctionExtendService functionExtendService : load) {
            methods.addAll(functionExtendService.extendMethods());
        }
        return methods;
    }

}
