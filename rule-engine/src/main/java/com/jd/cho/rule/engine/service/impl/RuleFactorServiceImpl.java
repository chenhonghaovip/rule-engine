package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.convert.RuleFactorConvert;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.service.RuleFactorService;
import com.jd.cho.rule.engine.service.dto.RuleFactorDTO;
import com.jd.cho.rule.engine.service.dto.RuleFactorQueryDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public String createRuleFactor(RuleFactorDTO ruleFactorDTO) {
        RuleFactor ruleFactor = RuleFactorConvert.INSTANCE.doToEntity(ruleFactorDTO);

        AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotBlank(ruleFactor.getFactorCode(), BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotNull(ruleFactor.getFactorType(), BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);
        ruleConfigGateway.createRuleFactor(ruleFactor);
        return null;
    }

    @Override
    public void updateRuleFactor(RuleFactorDTO ruleFactorDTO) {
        RuleFactor ruleFactor = RuleFactorConvert.INSTANCE.doToEntity(ruleFactorDTO);
        ruleConfigGateway.updateRuleFactor(ruleFactor);
    }

    @Override
    public List<RuleFactorQueryDTO> queryBySceneCode(Map<String, Object> context) {
        if (Objects.isNull(context) || Objects.isNull(context.get(Dict.SCENE_CODE))) {
            throw new BusinessException("场景编码不能为空");
        }
        String sceneCode = (String) context.get(Dict.SCENE_CODE);
        List<RuleFactor> ruleFactors = ruleConfigGateway.queryBySceneCode(sceneCode, context);
        Map<String, List<RuleFactor>> groupCodeMaps = ruleFactors.stream().collect(Collectors.groupingBy(RuleFactor::getGroupCode));

        return groupCodeMaps.entrySet().stream()
                .map(each -> RuleFactorQueryDTO.builder()
                        .groupCode(each.getKey())
                        .groupName(each.getValue().stream().findFirst().orElse(new RuleFactor()).getGroupName())
                        .ruleFactorBeans(each.getValue().stream().map(RuleFactorConvert.INSTANCE::doToDTO).collect(Collectors.toList())).build()).collect(Collectors.toList());

    }

    @Override
    public List<CommonDict> factorConstantValues(Map<String, Object> context) {
        if (Objects.isNull(context) || Objects.isNull(context.get(Dict.FACTOR_CODE))) {
            throw new BusinessException("规则因子编码不能为空");
        }
        return ruleConfigGateway.factorConstantValues(context);
    }
}
