package com.jd.cho.rule.engine.core.runner;

import com.jd.cho.rule.engine.domain.model.CustomMethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 */
public interface CoreExpressionRunner {
    /**
     * 添加类方法
     *
     * @param methods 方法集合
     * @throws Exception 异常信息
     */
    void addFunctionOfClassMethod(List<Method> methods) throws Exception;

    /**
     * 添加实例方法
     *
     * @param method 方法
     * @param object 对象
     * @throws Exception 异常信息
     */
    void addFunctionOfServiceMethod(Method method, Object object) throws Exception;

    /**
     * 执行规则
     *
     * @param statement    规则表达式
     * @param context      规则上下文信息
     * @param fieldMapping 规则因子映射关系
     * @return 规则执行结果
     */
    Object execute(String statement, Map<String, Object> context, Map<String, String> fieldMapping);

    /**
     * 执行规则
     *
     * @param statement 规则表达式
     * @param context   规则上下文信息
     * @return 规则执行结果
     */
    Object execute(String statement, Map<String, Object> context);

    /**
     * 获取输出变量名称
     *
     * @param statement 规则表达式
     * @return 变量名称
     */
    String[] getOutVarNames(String statement);

    /**
     * 获取输出函数名称
     *
     * @param statement 规则表达式
     * @return 函数名称
     */
    String[] getOutFunctionNames(String statement);

    /**
     * 获取全部自定义方法信息
     *
     * @return 方法
     */
    List<CustomMethod> getCustomMethodList();

    /**
     * 获取指定方法信息
     *
     * @param methodCode 方法code
     * @return 方法
     */
    CustomMethod getCustomMethod(String methodCode);
}
