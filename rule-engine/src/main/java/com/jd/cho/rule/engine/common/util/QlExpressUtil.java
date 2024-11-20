package com.jd.cho.rule.engine.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * （1）打通了spring容器，通过扩展IExpressContext->QLExpressContext
 * 获取本地变量的时候，可以获取到spring的bean
 * （2）在runner初始化的时候，使用了函数映射功能：addFunctionOfServiceMethod
 * （3）在runner初始化的时候，使用了代码映射功能：addMacro
 *
 * @author chenhonghao12
 */
@Slf4j
public class QlExpressUtil {
    public static final List<CustomMethod> CUSTOM_METHODS = Lists.newArrayList();
    private static final ExpressRunner RUNNER = new ExpressRunner();

    static {
        try {
            RUNNER.addFunctionOfClassMethod("isNotBlank", StringUtils.class, "isNotBlank", new Class[]{CharSequence.class}, null);
            RUNNER.addFunctionOfClassMethod("isBlank", StringUtils.class, "isBlank", new Class[]{CharSequence.class}, null);
            RUNNER.addFunctionOfClassMethod("isEmpty", CollectionUtils.class, "isEmpty", new Class[]{Collection.class}, null);
            RUNNER.addFunctionOfClassMethod("isNotEmpty", CollectionUtils.class, "isNotEmpty", new Class[]{Collection.class}, null);
            RUNNER.addFunctionOfClassMethod("containAnyOne", CollectionUtil.class, "containAnyOne", new Class[]{Collection.class, Collection.class}, null);
            RUNNER.addFunctionOfClassMethod("containsAll", CollectionUtil.class, "containsAll", new Class[]{Collection.class, Collection.class}, null);
            RUNNER.addFunctionOfClassMethod("changeArrayToList", CollectionUtil.class, "changeArrayToList", new Class[]{Object[].class}, null);
            RUNNER.addFunctionOfClassMethod("dateBefore", DateUtil.class, "dateBefore", new Class[]{Date.class, Date.class}, null);
            RUNNER.addFunctionOfClassMethod("dateAfter", DateUtil.class, "dateAfter", new Class[]{Date.class, Date.class}, null);
            RUNNER.addFunctionOfClassMethod("dateEqualDay", DateUtil.class, "dateEqualDay", new Class[]{Date.class, Date.class}, null);
            RUNNER.addFunctionOfClassMethod("dateEqual", DateUtil.class, "dateEqual", new Class[]{Date.class, Date.class}, null);

            List<Method> customFunctions = AtomicCustomFunctionUtil.getCustomFunction();
            addFunction(customFunctions);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void addFunction(List<Method> methods) throws Exception {
        List<CustomMethod> result = Lists.newArrayList();

        for (Method method : methods) {
            Class<?> declaringClass = method.getDeclaringClass();
            String methodName = method.getName();

            CustomMethod resolve = MethodUtil.resolve(method);
            Class<?>[] parameterTypes = method.getParameterTypes();
            RUNNER.addFunctionOfClassMethod(resolve.getMethodCode(), declaringClass, methodName, parameterTypes, null);
            result.add(resolve);
        }

        CUSTOM_METHODS.addAll(result);
    }


    /**
     * @param statement 执行语句
     * @param context   上下文
     */
    @SuppressWarnings("unchecked")
    public static Object execute(String statement, Map<String, Object> context, Map<String, String> fieldMapping) {
        try {
            return RUNNER.execute(statement, new QLExpressContext(context, fieldMapping), null, false, false);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={},context:{}", statement, JSON.toJSONString(context));
            throw new RuntimeException(e);
        }
    }


    /**
     * @param statement 执行语句
     * @param context   上下文
     */
    @SuppressWarnings("unchecked")
    public static Object execute(String statement, Map<String, Object> context) {
        try {
            return RUNNER.execute(statement, new QLExpressContext(context, null), null, false, false);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={},context:{}", statement, JSON.toJSONString(context));
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public static String[] getOutVarNames(String statement) {
        try {
            return RUNNER.getOutVarNames(statement);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={}", statement);
            throw new RuntimeException(e);
        }
    }
}
