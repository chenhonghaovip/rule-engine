package com.jd.cho.rule.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Configuration
@ComponentScan
@MapperScan({"com.jd.cho.rule.engine.dal.mapper"})
public class RuleEngineAutoConfiguration {

}
