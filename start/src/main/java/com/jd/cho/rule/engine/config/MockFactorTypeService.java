package com.jd.cho.rule.engine.config;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.core.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.core.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.spi.RuleFactorTypeExtendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class MockFactorTypeService implements RuleFactorTypeExtendService {

    @Override
    public FactorTypeDTO getFactorType() {
        List<ComparativeOperator> list = Lists.newArrayList();
        list.add(ComparativeOperator.builder().operator("ddd").remark("ddd").expression("dfa").code("test").build());
        return FactorTypeDTO.builder().code("test").desc("测试").comparativeOperatorList(list).build();
    }
}
