package com.jd.cho.rule.engine.common.util;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
    /**
     * 运行器
     */
    private static final ExpressRunner RUNNER = new ExpressRunner();

    /**
     * 自定义函数
     */
    private static final List<CustomMethod> CUSTOM_METHODS = Lists.newArrayList();

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行QL表达式
     *
     * @param statement    执行语句
     * @param context      上下文
     * @param fieldMapping 变量映射关系
     * @return 执行结果
     */
    public static Object execute(String statement, Map<String, Object> context, Map<String, String> fieldMapping) {
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
    public static Object execute(String statement, Map<String, Object> context) {
        try {
            return execute(statement, context, null);
        } catch (Exception e) {
            log.error("QlExpressUtil::execute error,statement={}", statement);
            throw new RuntimeException(e);
        }
    }

}
