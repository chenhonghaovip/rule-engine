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

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Component
public class CustomMethodProcessor implements SmartInitializingSingleton, BeanFactoryPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;
    @Resource
    private CoreExpressionRunner coreExpressionRunner;
    @Resource
    private List<CustomFunctionExtendService> customFunctionExtendServices;

    @Override
    public void afterSingletonsInstantiated() {
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
                        throw new BeanInitializationException("Failed to process @EventListener " +
                                "annotation on bean with name '" + beanName + "'", ex);
                    }
                }
            }
        }
    }

    private void processBean(final String beanName, final Class<?> targetType) throws Exception {
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
                    coreExpressionRunner.addFunctionOfServiceMethod(methodToUse, bean);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


        if (!CollectionUtils.isEmpty(customFunctionExtendServices)) {
            for (CustomFunctionExtendService functionExtendService : customFunctionExtendServices) {
                List<Method> methods = functionExtendService.extendMethods();
                if (!CollectionUtils.isEmpty(methods)) {
                    coreExpressionRunner.addFunctionOfClassMethod(methods);
                }
            }
        }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.beanFactory = configurableListableBeanFactory;
    }
}
