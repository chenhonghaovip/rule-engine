package com.jd.cho.rule.engine.infr.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author zhihei
 * @date 2023/8/16
 */
public class ApplicationUtils implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static ApplicationContext applicationContext;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ApplicationUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

    public static <T> T getBeans(String beanName, Class<T> t) {
        return applicationContext.getBean(beanName, t);
    }

    public static <T> T getBeans(Class<T> t) {
        return applicationContext.getBean(t);
    }

    public static <T> Map<String, T> getBeanByType(Class<T> t) {
        return applicationContext.getBeansOfType(t);
    }
}
