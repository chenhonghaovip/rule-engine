package com.jd.cho.rule.engine.spi;

import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.factor.ComparativeOperator;
import com.jd.cho.rule.engine.factor.dto.FactorTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleFactorTypeExtendService {

    /**
     * 获取支持的因子类型及操作符等信息
     *
     * @return 因子类型
     */
    FactorTypeDTO getFactorType();

    /**
     * 默认的内置因子类型处理逻辑
     *
     * @param factorTypeEnum 内置因子类型
     * @return 因子类型封装
     */
    default FactorTypeDTO getExpressOperationList(FactorTypeEnum factorTypeEnum) {
        AssertUtil.isNotNull(factorTypeEnum, BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);
        List<ExpressOperationEnum> operationByGroup = ExpressOperationEnum.getOperationByGroup(factorTypeEnum.getCode());
        return FactorTypeDTO.builder()
                .code(factorTypeEnum.getCode())
                .desc(factorTypeEnum.getDesc())
                .comparativeOperatorList(operationByGroup.stream().map(each -> ComparativeOperator.builder().operator(each.getOperator()).remark(each.getRemark()).expression(each.getExpression()).build()).collect(Collectors.toList()))
                .build();
    }
}
