package com.jd.cho.rule.engine.domain.atomic.impl;

import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.dal.mapper.RuleFactorDynamicSqlSupport;
import com.jd.cho.rule.engine.dal.mapper.RuleFactorMapper;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

        if (Objects.nonNull(ruleFactorDO) && StringUtils.isNotBlank(ruleFactorDO.getFactorScript())) {
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
