package com.jd.cho.rule.engine.factor;

import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.domain.model.RuleFactorType;
import com.jd.cho.rule.engine.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.spi.RuleFactorTypeExtendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Component
public class RuleFactorTypeLoader implements InitializingBean {
    public static final Map<String, RuleFactorType> FACTOR_EXPRESS_TYPES_MAPS = Maps.newHashMap();
    public static final Map<String, FactorTypeDTO> FACTOR_TYPE_DTO_MAP = Maps.newHashMap();

    @Resource
    private List<RuleFactorTypeExtendService> ruleFactorTypeExtendServices;

    @Override
    public void afterPropertiesSet() {
        for (RuleFactorTypeExtendService ruleFactorTypeExtendService : ruleFactorTypeExtendServices) {
            FactorTypeDTO factorType = ruleFactorTypeExtendService.getFactorType();

            checkInfo(factorType);

            List<ComparativeOperator> comparativeOperatorList = factorType.getComparativeOperatorList();
            List<RuleFactorType> ruleFactorTypes = comparativeOperatorList.stream().map(o ->
                    RuleFactorType.builder().code(factorType.getCode()).desc(factorType.getDesc())
                            .expression(o.getExpression()).remark(o.getRemark())
                            .operator(o.getOperator()).build()).collect(Collectors.toList());
            ruleFactorTypes.forEach(o -> {
                if (FACTOR_EXPRESS_TYPES_MAPS.containsKey(o.getOperator())) {
                    throw new BusinessException(BizErrorEnum.FACTOR_TYPE_IS_REPEAT);
                }
                FACTOR_EXPRESS_TYPES_MAPS.put(o.getOperator(), o);
            });
            FACTOR_TYPE_DTO_MAP.put(factorType.getCode(), factorType);
        }
    }


    /**
     * 基础校验
     *
     * @param factorType 校验信息
     */
    private void checkInfo(FactorTypeDTO factorType) {
        if (Objects.isNull(factorType) || CollectionUtils.isEmpty(factorType.getComparativeOperatorList()) || StringUtils.isBlank(factorType.getCode())) {
            throw new BusinessException(BizErrorEnum.FACTOR_TYPE_IS_ERROR);
        }
        if (FACTOR_TYPE_DTO_MAP.containsKey(factorType.getCode())) {
            throw new BusinessException(BizErrorEnum.FACTOR_TYPE_IS_REPEAT);
        }
    }
}
