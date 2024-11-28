package com.jd.cho.rule.engine.config;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.spi.RuleFactorExtendService;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class MockRuleFactorExtendService implements RuleFactorExtendService {

    @Override
    public List<RuleFactor> extendFactors(List<RuleFactor> ruleFactors, Map<String, Object> context) {
        List<RuleFactor> result = Lists.newArrayList();
        Map<String, List<RuleFactor>> ruleFactorMaps = ruleFactors.stream().collect(Collectors.groupingBy(RuleFactor::getGroupCode));
        List<RuleFactor> ruleFactors1 = ruleFactorMaps.get("11");
        if (CollectionUtils.isNotEmpty(ruleFactors1)) {
            for (int i = 1; i <= 4; i++) {
                final int index = i;
                List<RuleFactor> ruleFactorList = ruleFactors1.stream().map(each -> {
                    RuleFactor ruleFactor = RuleFactor.copyEntity(each);
                    ruleFactor.setFactorCode(index + "_" + each.getFactorCode());
                    ruleFactor.setGroupCode(index + "_" + each.getGroupCode());
                    return ruleFactor;
                }).collect(Collectors.toList());
                result.addAll(ruleFactorList);
            }
        }
        return result;
    }

}
