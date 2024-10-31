package com.jd.cho.rule.engine;

import com.jd.framework.boot.autoconfigure.DongBootApplication;
import com.jd.framework.boot.core.DongApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@DongBootApplication
@PropertySources(@PropertySource(value = { "classpath:/${spring.profiles.active}/application.properties", "classpath:/${spring.profiles.active}/jsf.properties" }, ignoreResourceNotFound = true))
public class RuleEngineApplication {

    public static void main(String[] args) {
        DongApplication.run(RuleEngineApplication.class, args);
    }

}
