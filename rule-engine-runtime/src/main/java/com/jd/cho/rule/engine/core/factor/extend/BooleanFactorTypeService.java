package com.jd.cho.rule.engine.core.factor.extend;

import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.core.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.spi.RuleFactorTypeExtendService;
import org.springframework.stereotype.Service;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class BooleanFactorTypeService implements RuleFactorTypeExtendService {

    @Override
    public FactorTypeDTO getFactorType() {
        FactorTypeEnum factorTypeEnum = FactorTypeEnum.BOOLEAN;
        return getExpressOperationList(factorTypeEnum);
    }
}
