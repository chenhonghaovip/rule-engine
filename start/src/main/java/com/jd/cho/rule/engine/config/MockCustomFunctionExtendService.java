package com.jd.cho.rule.engine.config;

import com.jd.cho.rule.engine.spi.CustomFunctionExtendService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class MockCustomFunctionExtendService implements CustomFunctionExtendService {
    @Override
    public List<Method> extendMethods() {
        Method[] declaredMethods = TestUtil.class.getDeclaredMethods();
        return Arrays.stream(declaredMethods).collect(Collectors.toList());
    }
}
