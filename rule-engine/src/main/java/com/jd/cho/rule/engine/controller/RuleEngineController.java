package com.jd.cho.rule.engine.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.service.RuleEngineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static com.jd.cho.rule.engine.common.enums.ExpressOperationEnum.BOOLEAN_IS_TRUE;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@RestController
@RequestMapping("/rule/engine")
public class RuleEngineController {

    @Resource
    private RuleEngineService ruleEngineService;

    /**
     * 获取因子类型列表
     *
     * @param context 请求参数
     */
    @PostMapping("/run")
    public Map<String, Object> execute(@RequestBody Map<String, Object> context) {
        String rulePackCode = (String) context.get("rulePackCode");
        context.put("b", new Date());
        context.put("d", null);
        ruleEngineService.execute(rulePackCode, context);
        if (Objects.nonNull(context.get(Dict.RESULT_ALIAS))) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            return new Gson().fromJson(JSON.toJSONString(context.get(Dict.RESULT_ALIAS)), type);
        }
        return null;
    }

    /**
     * stencil
     * 值传递和引用传递
     */
    @GetMapping("/transfer")
    public void transfer() {
        try {
            Object value = 100;
            String format1 = String.format(BOOLEAN_IS_TRUE.getExpression(), "a", value);
            Map<String, Object> maps = Maps.newHashMap();
            maps.put("a", false);
            String[] outVarNames = QlExpressUtil.getOutVarNames(format1);
            System.out.println("outVarNames:" + outVarNames);

            Object execute = QlExpressUtil.execute(format1, maps);
            System.out.println(execute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
