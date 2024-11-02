package com.jd.cho.rule.engine.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(DataSource.class)
@ComponentScan("com.jd.cho.rule.engine")
@MapperScan({"com.jd.cho.rule.engine.infr.customer"})
public class RuleEngineAutoConfiguration {


    @Bean
    public Test test() {
        return new Test();
    }


    public static class Test {
    }
}
