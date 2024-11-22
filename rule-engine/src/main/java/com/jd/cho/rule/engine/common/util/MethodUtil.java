package com.jd.cho.rule.engine.common.util;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.anno.ApiParam;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class MethodUtil {

    public static CustomMethod resolve(Method method) {
        CustomMethod customMethod = new CustomMethod();
        ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
        if (Objects.nonNull(apiMethod)) {
            customMethod.setMethodName(apiMethod.name());
            customMethod.setMethodCode(apiMethod.code());
        } else {
            customMethod.setMethodName(method.getName());
            customMethod.setMethodCode(method.getName());
        }

        StringBuilder express = new StringBuilder();
        Class<?>[] parameterTypes = method.getParameterTypes();
        customMethod.setParamCount(parameterTypes.length);
        customMethod.setReturnType(method.getReturnType().getName());
        express.append(customMethod.getMethodCode()).append(Dict.LEFT_BRACKETS);

        List<CustomMethod.CustomMethodParam> result = Lists.newArrayList();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            String name = "入参";
            Annotation annotation = Arrays.stream(parameterAnnotation).filter(each -> each.annotationType().equals(ApiParam.class)).findFirst().orElse(null);
            if (Objects.nonNull(annotation)) {
                ApiParam apiParam = (ApiParam) annotation;
                name = StringUtils.isNotBlank(apiParam.name()) ? apiParam.name() : name;
            }

            CustomMethod.CustomMethodParam customMethodParam = new CustomMethod.CustomMethodParam();
            customMethodParam.setParamName(name);
            customMethodParam.setParamType(parameterTypes[i]);

            express.append(getMethodExpression(parameterTypes[i]));
            if (i != parameterAnnotations.length - 1) {
                express.append(Dict.SPLIT);
            }
            result.add(customMethodParam);
        }
        express.append(Dict.RIGHT_BRACKETS);
        customMethod.setMethodExpression(express.toString());
        customMethod.setCustomMethodParams(result);
        return customMethod;
    }

    /**
     * 解析方法参数类型转换为QL表达式
     *
     * @param paramType
     * @return
     */

    private static String getMethodExpression(Class<?> paramType) {
        if (paramType.isPrimitive()) {
            return "%s";
        }
        if (paramType.isAssignableFrom(Date.class)) {
            return "new Date(%s)";
        }
        if (paramType.isAssignableFrom(Collection.class)) {
            return "changeArrayToList(%s)";
        }
        if (paramType.isAssignableFrom(Collection.class)) {
            return "changeArrayToList(%s)";
        }
        if (paramType.isAssignableFrom(String.class)) {
            return "\"%s\"";
        }
        if (paramType.isAssignableFrom(Boolean.class)) {
            return "Boolean.TRUE.equals(%s)";
        }

        throw new RuntimeException("不支持的类型");

    }

    @Test
    public void test() {
        System.out.println(Date.class.isAssignableFrom(Date.class));
    }
}
