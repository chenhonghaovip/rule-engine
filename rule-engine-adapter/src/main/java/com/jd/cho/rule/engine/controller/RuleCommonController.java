package com.jd.cho.rule.engine.controller;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.common.util.MethodUtil;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.core.RuleGroupExtendServiceFactory;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import com.jd.cho.rule.engine.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.factor.dto.FactorTypeDTO;
import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/common")
@AllArgsConstructor
public class RuleCommonController {

    private RuleGroupExtendServiceFactory ruleGroupExtendServiceFactory;

    /**
     * 获取因子类型列表
     *
     * @return 因子支持类型
     */
    @GetMapping("/factor/type")
    public List<FactorTypeDTO> factorTypeEnums() {
        return new ArrayList<>(RuleFactorTypeLoader.FACTOR_TYPE_DTO_MAP.values());
    }


    /**
     * 获取因子类型支持的操作符号
     *
     * @return 因子支持操作符号
     */
    @GetMapping("/factor/express")
    public List<ComparativeOperator> expressOperationEnums(@RequestParam("type") String type) {
        FactorTypeDTO factorTypeDTO = RuleFactorTypeLoader.FACTOR_TYPE_DTO_MAP.get(type);
        if (Objects.isNull(factorTypeDTO)) {
            throw new BusinessException(BizErrorEnum.FACTOR_TYPE_IS_ERROR);
        }
        return factorTypeDTO.getComparativeOperatorList();
//        return ExpressOperationEnum.getOperationByGroup(type);
    }

    /**
     * 获取因子常量类型列表
     *
     * @return 常量类型
     */
    @GetMapping("/factor/const")
    public List<ConstantEnum> constantEnums() {
        return ConstantEnum.CONSTANT_ENUMS;
    }

    /**
     * 获取包内规则调度策略
     *
     * @return 规则类型
     */
    @GetMapping("/rule/strategy")
    public List<CommonDict> ruleArrangeStrategyEnums() {
        return ruleGroupExtendServiceFactory.allRuleGroup();
    }


    /**
     * 获取规则类型列表
     *
     * @return 规则类型
     */
    @GetMapping("/rule/type")
    public List<RulePackTypeEnum> rulePackTypeEnums() {
        return RulePackTypeEnum.RULE_PACK_TYPE_ENUMS;
    }


    /**
     * 获取支持类型
     *
     * @return 可支持类型
     */
    @GetMapping("ruleTypeEnums")
    public List<VarTypeEnum> ruleTypeEnums() {
        return VarTypeEnum.RULE_PACK_TYPE_ENUMS;
    }

    /**
     * 获取全局自定义函数方法
     *
     * @return 函数方法
     */
    @GetMapping("/methods")
    public List<CustomMethod> getMethod() {
        return QlExpressUtil.CUSTOM_METHODS;
    }

    /**
     * 获取全局自定义函数方法的详细信息（出入参数、返回值类型、枚举选项等）
     *
     * @return 函数方法
     */
    @PostMapping("/methodInfo")
    public List<CommonDict> getMethodInfo(@RequestBody Map<String, Object> context) {
        AssertUtil.isNotNull(context.get(Dict.METHOD_CODE));
        return MethodUtil.getMethodConstants(context);
    }


}
