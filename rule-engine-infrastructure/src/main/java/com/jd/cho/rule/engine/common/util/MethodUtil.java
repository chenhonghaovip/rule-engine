package com.jd.cho.rule.engine.common.util;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
public class MethodUtil {
    /**
     * 获取字典
     *
     * @param constantType  常量类型
     * @param constantValue 常量值
     * @return 字典
     */
    public static List<CommonDict> getDict(String constantType, String constantValue, Map<String, Object> context) {
        if (ConstantEnum.INPUT.getCode().equals(constantType)) {
            return new ArrayList<>();
        } else if (ConstantEnum.SCRIPT.getCode().equals(constantType)) {
            constantValue = JSON.toJSONString(ApplicationUtils.getBeans(CoreExpressionRunner.class).execute(constantValue, context));
        }
        return JSON.parseArray(constantValue, CommonDict.class);
    }
}
