package com.jd.cho.rule.engine.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.anno.ApiParam;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.model.RuleFactorType;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
public class MethodUtil {

    /**
     * 解析方法信息
     *
     * @param method 方法
     * @return 解析后的方法信息
     */
    public static CustomMethod resolve(Method method) {
        ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
        if (Objects.isNull(apiMethod)) {
            log.error("current method ignore method annotation:{}", method.getName());
            throw new BusinessException(BizErrorEnum.CUSTOM_METHOD_NOT_HAVE_ANNOTATION);
        }

        // 构建方法code、方法名称、方法返回值类型
        CustomMethod customMethod = new CustomMethod();
        customMethod.setMethodName(StringUtils.isNotBlank(apiMethod.name()) ? apiMethod.name() : method.getName());
        customMethod.setMethodCode(StringUtils.isNotBlank(apiMethod.code()) ? apiMethod.code() : method.getName());
        RuleFactorType factorType = ApplicationUtils.getBeans(RuleFactorTypeLoader.class).getFactorType(apiMethod.returnType());
        if (Objects.isNull(factorType)) {
            throw new BusinessException(BizErrorEnum.RETURN_TYPE_NOT_EXIST);
        }
        customMethod.setReturnType(factorType);

        // 设置当前方法的返回值枚举
        String valueScript = apiMethod.valueScript();
        if (StringUtils.isNotBlank(valueScript)) {
            customMethod.setConstantType(ConstantEnum.SCRIPT);
            Object execute = QlExpressUtil.execute(valueScript, Maps.newHashMap());
            Optional.ofNullable(execute).ifPresent(each -> customMethod.setConstantValues(JSON.parseArray(JSON.toJSONString(execute), CommonDict.class)));
        } else {
            customMethod.setConstantType(ConstantEnum.INPUT);
        }

        // 构建方法的QL表达式和入参信息
        StringBuilder express = new StringBuilder();
        Class<?>[] parameterTypes = method.getParameterTypes();
        customMethod.setParamCount(parameterTypes.length);
        express.append(customMethod.getMethodCode()).append(Dict.LEFT_BRACKETS);

        List<CustomMethod.CustomMethodParam> result = Lists.newArrayList();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            String name = "入参";
            Annotation annotation = Arrays.stream(parameterAnnotation).filter(each -> each.annotationType().equals(ApiParam.class)).findFirst().orElse(null);
            if (Objects.nonNull(annotation)) {
                ApiParam apiParam = (ApiParam) annotation;
                name = StringUtils.isNotBlank(apiParam.name()) ? apiParam.name() : name;
            }

            CustomMethod.CustomMethodParam customMethodParam = new CustomMethod.CustomMethodParam();
            customMethodParam.setParamName(name);
            customMethodParam.setIsSysClassType(isSysParamType(parameterType));
            customMethodParam.setParamType(parameterType);

            express.append(getMethodExpression(parameterType));
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
     * @param paramType 参数类型
     * @return ql表达式
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
        if (paramType.isAssignableFrom(String.class)) {
            return "%s";
        }
        if (paramType.isAssignableFrom(Boolean.class)) {
            return "Boolean.TRUE.equals(%s)";
        }
        return "%s";
    }

    /**
     * 判断是否系统内置参数类型
     *
     * @param paramType 参数类型
     * @return trule/false
     */
    public static boolean isSysParamType(Class<?> paramType) {
        if (paramType.isPrimitive()) {
            return true;
        }
        if (paramType.isAssignableFrom(Date.class)) {
            return true;
        }
        if (paramType.isAssignableFrom(Collection.class)) {
            return true;
        }
        if (paramType.isAssignableFrom(String.class)) {
            return true;
        }
        if (paramType.isAssignableFrom(Boolean.class)) {
            return true;
        }
        return paramType.isAssignableFrom(Integer.class);
    }


    /**
     * 获取字典
     *
     * @param constantType  常量类型
     * @param constantValue 常量值
     * @return 字典
     */
    public static List<CommonDict> getDict(String constantType, String constantValue, Map<String, Object> context) {
        if (ConstantEnum.INPUT.getCode().equals(constantType)) {
            return Lists.newArrayList();
        } else if (ConstantEnum.SCRIPT.getCode().equals(constantType)) {
            constantValue = JSON.toJSONString(QlExpressUtil.execute(constantValue, context));
        }
        return JSON.parseArray(constantValue, CommonDict.class);
    }
}
