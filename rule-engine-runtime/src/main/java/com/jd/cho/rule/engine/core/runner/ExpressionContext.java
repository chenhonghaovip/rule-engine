package com.jd.cho.rule.engine.core.runner;

import com.jd.cho.rule.engine.core.atomic.FactorValueService;
import com.ql.util.express.IExpressContext;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class ExpressionContext extends HashMap<String, Object> implements IExpressContext<String, Object> {

    private final ApplicationContext context;

    private final Map<String, String> fieldMapping;

    private final FactorValueService factorValueService;

    public ExpressionContext(Map<String, Object> map, Map<String, String> fieldMapping,
                             ApplicationContext context, FactorValueService factorValueService) {
        super(map);
        this.fieldMapping = fieldMapping;
        this.context = context;
        this.factorValueService = factorValueService;
    }

    /**
     * 抽象方法：根据名称从属性列表中提取属性值
     */
    @Override
    public Object get(Object name) {
        boolean containsKey = super.containsKey(name);
        if (containsKey) {
            return super.get(name);
        }

        if (Objects.nonNull(context) && context.containsBean((String) name)) {
            // 如果在Spring容器中包含bean，则返回String的Bean
            return context.getBean((String) name);
        }
        return factorValueService.getFieldValue(name, this, fieldMapping);
    }


    @Override
    public Object put(String name, Object object) {
        return super.put(name, object);
    }
}
