package com.jd.cho.rule.engine.common.cache;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class ContextHolder {
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    /**
     * 设置全局上下文信息
     *
     * @param context context
     */
    public static void setContext(Map<String, Object> context) {
        CONTEXT.set(context);
    }

    /**
     * 获取全局上下文
     *
     * @return 全局上下文
     */
    public static Map<String, Object> getContext() {
        return CONTEXT.get();
    }

    /**
     * 清除上下文信息
     */
    public static void clear() {
        CONTEXT.remove();
    }
}
