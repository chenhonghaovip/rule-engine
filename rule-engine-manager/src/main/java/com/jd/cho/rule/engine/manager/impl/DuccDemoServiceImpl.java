package com.jd.cho.rule.engine.manager.impl;

import com.jd.cho.rule.engine.manager.DuccDemoService;
import com.jd.laf.config.spring.annotation.LafValue;
import org.springframework.stereotype.Service;


/**
 * Description: DUCC Demo
 *
 * @author DongBoot
 * @see <a href="https://joyspace.jd.com/pages/s8A75GLGvGiFbRA4ufZa">DUCC使用说明</a>
 * @see <a href="https://taishan.jd.com/ducc/web/nswork">DUCC管理端</a>
 */
@Service
public class DuccDemoServiceImpl implements DuccDemoService {


    @LafValue("dongboot.ducc.test")
    private String message;


    /**
     * 获取DUCC动态配置参数
     *
     * @return 参数值
     */
    @Override
    public String getMessage() {
        return message;
    }

}