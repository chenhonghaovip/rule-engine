package com.jd.cho.rule.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication
@MapperScan({"com.jd.cho.rule.engine.dal.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
