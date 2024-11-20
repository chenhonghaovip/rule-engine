package com.jd.cho.rule.engine.common.util;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.anno.ApiParam;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        }

        Class<?>[] parameterTypes = method.getParameterTypes();
        customMethod.setParamCount(parameterTypes.length);
        customMethod.setReturnType(method.getReturnType().getName());

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
            result.add(customMethodParam);
        }
        customMethod.setCustomMethodParams(result);
        return customMethod;
    }
}
