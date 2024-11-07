package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.service.RuleFactorService;
import com.jd.cho.rule.engine.service.dto.RuleFactorEditDTO;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleFactorServiceImpl implements RuleFactorService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createRuleFactor(RuleFactorEditDTO ruleFactorDTO) {
        return null;
    }

    @Override
    public void updateRuleFactor(RuleFactorEditDTO ruleFactorDTO) {

    }

    @Override
    public List<RuleFactorQueryDTO> queryBySceneCode(String sceneCode) {
        List<RuleFactor> ruleFactors = ruleConfigGateway.queryBySceneCode(sceneCode);
        Map<String, List<RuleFactor>> groupCodeMaps = ruleFactors.stream().collect(Collectors.groupingBy(RuleFactor::getGroupCode));

        return groupCodeMaps.entrySet().stream()
                .map(each -> RuleFactorQueryDTO.builder()
                        .groupCode(each.getKey())
                        .groupName(each.getValue().stream().findFirst().orElse(new RuleFactor()).getGroupName())
                        .ruleFactorBeans(each.getValue().stream().map(RuleFactorConvert.INSTANCE::doToDTO).collect(Collectors.toList())).build()).collect(Collectors.toList());

    }
}
