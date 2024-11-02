package com.jd.cho.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(DataSource.class)
@ComponentScan("com.jd.cho")
@MapperScan("com.jd.cho")
public class RuleEngineAutoConfiguration {

}
