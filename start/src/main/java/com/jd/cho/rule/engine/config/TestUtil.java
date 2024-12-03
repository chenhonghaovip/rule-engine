package com.jd.cho.rule.engine.config;


import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.anno.ApiParam;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class TestUtil {

    @ApiMethod(code = "getInfo", name = "获取信息", valueScript = "Spring.getBean(\"signDomainServiceImpl\").getDict();")
    public static String getInfo(@ApiParam(name = "节点编码") String nodeCode, @ApiParam(name = "因子") String factorCode) {
        System.out.println("fafafdaefafae");
        return "4564";
    }
}
