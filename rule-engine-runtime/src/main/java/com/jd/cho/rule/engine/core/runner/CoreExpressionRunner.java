package com.jd.cho.rule.engine.core.runner;

import com.jd.cho.rule.engine.domain.model.CustomMethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface CoreExpressionRunner {
    void addFunctionOfClassMethod(List<Method> methods) throws Exception;

    void addFunctionOfServiceMethod(Method method, Object object) throws Exception;

    Object execute(String statement, Map<String, Object> context, Map<String, String> fieldMapping);

    Object execute(String statement, Map<String, Object> context);

    String[] getOutVarNames(String statement);

    String[] getOutFunctionNames(String statement);

    List<CustomMethod> getCustomMethodList();

    CustomMethod getCustomMethod(String methodCode);
}
