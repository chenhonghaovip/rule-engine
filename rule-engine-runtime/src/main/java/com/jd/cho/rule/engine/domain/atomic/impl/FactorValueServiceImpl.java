package com.jd.cho.rule.engine.domain.atomic.impl;

import com.jd.cho.rule.engine.common.cache.ContextHolder;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.domain.atomic.FactorValueService;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
public class FactorValueServiceImpl implements FactorValueService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public Object getFieldValue(Object fieldName, Map<String, Object> context, Map<String, String> fieldMapping) {
        log.info("FactorValueService::getFieldValue,fieldName:{},param:{},fieldMapping:{}", fieldName, context, fieldMapping);
        String factorCode = (String) fieldName;
        String realFactorCode = fieldMapping.getOrDefault(factorCode, factorCode);

        context.put(Dict.FACTOR_CODE, factorCode);
        ContextHolder.setContext(context);

        List<RuleFactor> ruleFactors = ruleConfigGateway.queryFactorCodes();
        RuleFactor ruleFactor = ruleFactors.stream().filter(each -> each.getFactorCode().equals(realFactorCode)).findFirst().orElse(null);

        if (Objects.nonNull(ruleFactor) && StringUtils.isNotBlank(ruleFactor.getFactorScript())) {
            Object execute = QlExpressUtil.execute(ruleFactor.getFactorScript(), context);
            log.info("FactorValueService::getFieldValue,fieldName:{},value:{}", fieldName, execute);
            return execute;
        }

        log.error("规则因子{}执行异常，不存在全局上下文且不存在因子脚本", factorCode);
        throw new BusinessException(BizErrorEnum.FACTOR_CODE_RUN_ERROR);
    }
}
