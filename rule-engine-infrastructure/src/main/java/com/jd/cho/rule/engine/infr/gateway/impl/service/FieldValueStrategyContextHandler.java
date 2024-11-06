package com.jd.cho.rule.engine.infr.gateway.impl.service;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.infr.common.QlExpressUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Component
public class FieldValueStrategyContextHandler implements FieldValueStrategyContext {
    @Override
    public Object getFieldValue(Object fieldName, Map<String, Object> param) {
        String fieldNames = (String) fieldName;
        String[] splits = fieldNames.split("_");
        String node = null;
        String realFieldName = null;
        if (splits.length == 2) {
            node = splits[0];
            param.put(node, node);
            realFieldName = splits[1];
        } else {
            realFieldName = fieldNames;
        }

        // 通过fieldName的全路径获取字段配置信息
        if (!Lists.newArrayList("a", "b").contains(realFieldName)) {
            return null;
        }
        String script = "signDomainService.doSomeThing();" + "return NewList(\"11aa\", \"23\", \"3\");";
        FieldConfig build = FieldConfig.builder().fieldFullCode(String.valueOf(fieldName)).script(script).build();
        return QlExpressUtil.execute(build.getScript(), param, null);
    }


    @Data
    @AllArgsConstructor
    @Builder
    public static class FieldConfig {
        private String fieldCode;

        private String fieldFullCode;

        private String script;
    }
}
