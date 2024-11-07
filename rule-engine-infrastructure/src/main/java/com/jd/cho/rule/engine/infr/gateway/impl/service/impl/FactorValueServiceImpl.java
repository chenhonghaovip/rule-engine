package com.jd.cho.rule.engine.infr.gateway.impl.service.impl;

import com.jd.cho.rule.engine.infr.common.QlExpressUtil;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleFactorDynamicSqlSupport;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleFactorMapper;
import com.jd.cho.rule.engine.infr.gateway.impl.service.FactorValueService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
public class FactorValueServiceImpl implements FactorValueService {

    @Resource
    private RuleFactorMapper ruleFactorMapper;


    @Override
    public Object getFieldValue(Object fieldName, Map<String, Object> param, Map<String, String> fieldMapping) {
        log.info("FactorValueService::getFieldValue,fieldName:{},param:{},fieldMapping:{}", fieldName, param, fieldMapping);
        String factorName = (String) fieldName;
        if (fieldMapping.containsKey(factorName)) {
            factorName = fieldMapping.get(factorName);
        }
        String realFactorName = factorName;

        List<RuleFactorDO> ruleFactors = ruleFactorMapper.select(s -> s.where(RuleFactorDynamicSqlSupport.factorCode, isEqualTo(realFactorName)));
        RuleFactorDO ruleFactorDO = ruleFactors.stream().findFirst().orElse(null);

        if (Objects.nonNull(ruleFactorDO)) {
//            String script = "signDomainService.doSomeThing();" + "return NewList(\"11aa\", \"23\", \"3\");";
//            FieldConfig build = FieldConfig.builder().fieldFullCode(String.valueOf(fieldName)).script(script).build();
            return QlExpressUtil.execute(ruleFactorDO.getFactorScript(), param);
        }

        return null;
    }


    @Data
    @AllArgsConstructor
    @Builder
    public static class FieldConfig {
        private String fieldCode;

        private String fieldFullCode;

        private String script;
    }
}
