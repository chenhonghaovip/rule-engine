package com.jd.cho.rule.engine.adapter.convert;

import com.jd.cho.rule.engine.adapter.dto.RuleFactorDTO;
import com.jd.cho.rule.engine.controller.VO.req.RuleFactorReq;
import com.jd.cho.rule.engine.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.factor.model.RuleFactorType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AdapterConverters {
    private final RuleFactorConvert ruleFactorConvert;
    private final RuleFactorTypeLoader ruleFactorTypeLoader;

    public RuleFactorDTO doToDTO(RuleFactorReq ruleFactorReq) {
        RuleFactorDTO ruleFactorDTO = ruleFactorConvert.doToDTO(ruleFactorReq);
        if (ruleFactorDTO != null) {
            RuleFactorType factorType = ruleFactorTypeLoader.getFactorType(ruleFactorReq.getFactorType());
            ruleFactorDTO.setFactorType(factorType);
        }

        return ruleFactorDTO;
    }
}
