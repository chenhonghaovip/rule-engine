package com.jd.cho.rule.engine.factor;

import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.factor.model.RuleFactorType;
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

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Component
public class RuleFactorTypeLoader implements InitializingBean {
    public static final Map<String, ComparativeOperator> EXPRESS_TYPES_MAPS = Maps.newHashMap();
    public static final Map<String, FactorTypeDTO> FACTOR_TYPE_DTO_MAP = Maps.newHashMap();

    @Resource
    private List<RuleFactorTypeExtendService> ruleFactorTypeExtendServices;

    @Override
    public void afterPropertiesSet() {
        for (RuleFactorTypeExtendService ruleFactorTypeExtendService : ruleFactorTypeExtendServices) {
            FactorTypeDTO factorType = ruleFactorTypeExtendService.getFactorType();

            checkInfo(factorType);

            List<ComparativeOperator> comparativeOperatorList = factorType.getComparativeOperatorList();
            comparativeOperatorList.forEach(each -> {
                if (EXPRESS_TYPES_MAPS.containsKey(each.getOperator())) {
                    throw new BusinessException(BizErrorEnum.EXPRESSION_CODE_IS_REPEAT);
                }
                each.setCode(factorType.getCode());
                EXPRESS_TYPES_MAPS.put(each.getOperator(), each);
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


    public static RuleFactorType getFactorType(String factorType) {
        FactorTypeDTO factorTypeDTO = FACTOR_TYPE_DTO_MAP.get(factorType);
        return RuleFactorType.builder().code(factorTypeDTO.getCode()).desc(factorTypeDTO.getDesc()).build();
    }
}
