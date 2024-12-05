package com.jd.cho.rule.engine.core.runner.ql;

import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.common.util.CollectionUtil;
import com.jd.cho.rule.engine.common.util.DateUtil;
import com.jd.cho.rule.engine.common.util.QLExpressContext;
import com.jd.cho.rule.engine.core.method.CustomMethodResolver;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
@Slf4j
public class QLExpressionRunner implements CoreExpressionRunner {
    private final CustomMethodResolver customMethodResolver;
    /**
     * 运行器
     */
    private final ExpressRunner RUNNER = new ExpressRunner();

    /**
     * 自定义函数
     */
    private final List<CustomMethod> CUSTOM_METHODS = new ArrayList<>();

    public QLExpressionRunner(CustomMethodResolver customMethodResolver) {
        this.customMethodResolver = customMethodResolver;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加自定义函数
     *
     * @param methods 自定义方法列表
     * @throws Exception 异常信息
     */
    @Override
    public void addFunctionOfClassMethod(List<Method> methods) throws Exception {
        List<CustomMethod> result = new ArrayList<>();

        for (Method method : methods) {
            Class<?> declaringClass = method.getDeclaringClass();
            String methodName = method.getName();
            CustomMethod resolve = customMethodResolver.resolve(method);
            Class<?>[] parameterTypes = method.getParameterTypes();
            RUNNER.addFunctionOfClassMethod(resolve.getMethodCode(), declaringClass, methodName, parameterTypes, null);
            result.add(resolve);
        }
        CUSTOM_METHODS.addAll(result);
    }

    /**
     * 添加自定义函数
     *
     * @param method 自定义方法
     * @param object 执行对象实例
     * @throws Exception 异常信息
     */
    @Override
    public void addFunctionOfServiceMethod(Method method, Object object) throws Exception {
        String methodName = method.getName();
        CustomMethod resolve = customMethodResolver.resolve(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        RUNNER.addFunctionOfServiceMethod(resolve.getMethodCode(), object, methodName, parameterTypes, null);
        CUSTOM_METHODS.add(resolve);
    }

    /**
     * 执行QL表达式
     *
     * @param statement    执行语句
     * @param context      上下文
     * @param fieldMapping 变量映射关系
     * @return 执行结果
     */
    @Override
    public Object execute(String statement, Map<String, Object> context, Map<String, String> fieldMapping) {
        try {
            context.putIfAbsent(Dict.INNER_CONTEXT, ApplicationUtils.getApplicationContext());
            return RUNNER.execute(statement, new QLExpressContext(context, fieldMapping), null, false, false);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,context:{}", statement, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行QL表达式
     *
     * @param statement 执行语句
     * @param context   上下文
     * @return 执行结果
     */
    @Override
    public Object execute(String statement, Map<String, Object> context) {
        try {
            return execute(statement, context, null);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={}", statement);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取QLExpress表达式中的变量名称
     *
     * @param statement 表达式
     * @return 变量列表
     */
    @Override
    public String[] getOutVarNames(String statement) {
        try {
            return RUNNER.getOutVarNames(statement);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={}", statement);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取QLExpress表达式中的函数名称
     *
     * @param statement 表达式
     * @return 函数列表
     */
    @Override
    public String[] getOutFunctionNames(String statement) {
        try {
            return RUNNER.getOutFunctionNames(statement);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={}", statement);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取业务方的自定义函数方法
     *
     * @return 自定义函数列表
     */
    @Override
    public List<CustomMethod> getCustomMethodList() {
        return new ArrayList<>(CUSTOM_METHODS);
    }

    /**
     * 通过方法code获取自定义函数信息
     *
     * @param methodCode 方法code，唯一标识
     * @return 自定义函数信息
     */
    @Override
    public CustomMethod getCustomMethod(String methodCode) {
        return CUSTOM_METHODS.stream().filter(each -> each.getMethodCode().equals(methodCode)).findFirst().orElse(null);
    }
}
