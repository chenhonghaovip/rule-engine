package com.jd.cho.rule.engine.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Configuration
@ComponentScan("com.jd.cho.rule.engine")
@MapperScan({"com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper"})
public class RuleEngineAutoConfiguration {

}
