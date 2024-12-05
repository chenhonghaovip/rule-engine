package com.jd.cho.rule.engine.method;

import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.spi.CustomFunctionExtendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 该类主要用于解析自定义方法，并注册到规则引擎中
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Component
public class CustomMethodProcessor implements SmartInitializingSingleton, BeanFactoryPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void afterSingletonsInstantiated() {
        loadFunctionExtendServices();

        String[] beanNamesForType = beanFactory.getBeanNamesForType(Object.class);
        for (String beanName : beanNamesForType) {
            if (!ScopedProxyUtils.isScopedTarget(beanName)) {
                Class<?> type = null;
                try {
                    type = AutoProxyUtils.determineTargetClass(beanFactory, beanName);
                } catch (Throwable ignored) {
                }
                if (type != null) {
                    if (ScopedObject.class.isAssignableFrom(type)) {
                        try {
                            Class<?> targetClass = AutoProxyUtils.determineTargetClass(
                                    beanFactory, ScopedProxyUtils.getTargetBeanName(beanName));
                            if (targetClass != null) {
                                type = targetClass;
                            }
                        } catch (Throwable ignored) {

                        }
                    }
                    try {
                        // 核心功能在于此处
                        processBean(beanName, type);
                    } catch (Throwable ex) {
                        throw new BeanInitializationException("Failed to process @ApiMethod " +
                                "annotation on bean with name '" + beanName + "'", ex);
                    }
                }
            }
        }
    }

    /**
     * 解析IOC容器中被注解@ApiMethod标记的方法，并注册到规则引擎中
     *
     * @param beanName   bean实例名称
     * @param targetType 被注解的类
     */
    private void processBean(final String beanName, final Class<?> targetType) {
        Map<Method, ApiMethod> annotatedMethods = null;
        try {
            annotatedMethods = MethodIntrospector.selectMethods(targetType,
                    (MethodIntrospector.MetadataLookup<ApiMethod>) method ->
                            AnnotatedElementUtils.findMergedAnnotation(method, ApiMethod.class));
        } catch (Throwable ignored) {
        }
        if (!CollectionUtils.isEmpty(annotatedMethods)) {
            for (Map.Entry<Method, ApiMethod> entry : annotatedMethods.entrySet()) {
                Method method = entry.getKey();
                Object bean = beanFactory.getBean(beanName);
                Method methodToUse = AopUtils.selectInvocableMethod(method, beanFactory.getType(beanName));
                try {
                    CoreExpressionRunner coreExpressionRunner = beanFactory.getBean(CoreExpressionRunner.class);
                    coreExpressionRunner.addFunctionOfServiceMethod(methodToUse, bean);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 加载自定义方法
     */
    private void loadFunctionExtendServices() {
        try {
            Map<String, CustomFunctionExtendService> beansOfType = beanFactory.getBeansOfType(CustomFunctionExtendService.class);
            if (!CollectionUtils.isEmpty(beansOfType)) {
                for (CustomFunctionExtendService functionExtendService : beansOfType.values()) {
                    List<Method> methods = functionExtendService.extendMethods();
                    if (!CollectionUtils.isEmpty(methods)) {
                        CoreExpressionRunner coreExpressionRunner = beanFactory.getBean(CoreExpressionRunner.class);
                        coreExpressionRunner.addFunctionOfClassMethod(methods);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.beanFactory = configurableListableBeanFactory;
    }
}
