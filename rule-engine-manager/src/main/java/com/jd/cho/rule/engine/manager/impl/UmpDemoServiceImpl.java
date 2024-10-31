package com.jd.cho.rule.engine.manager.impl;

import com.jd.cho.rule.engine.manager.UmpDemoService;
import com.jd.ump.annotation.JProEnum;
import com.jd.ump.annotation.JProfiler;
import com.jd.ump.profiler.CallerInfo;
import com.jd.ump.profiler.proxy.Profiler;

/**
* Description: Ump Demo
*
* @author DongBoot
* @see <a href="https://joyspace.jd.com/pages/EyinifVCoYBZKpEhcukj">Ump Starter使用说明</a>
* @see <a href="https://taishan.jd.com/ump/monitor">Ump管理端</a>
*/
public class UmpDemoServiceImpl implements UmpDemoService {

    /**
    *  通过注解方式使用ump
    */
    // 只有当抛出的异常是继承于 SomeIncludeException，并且不继承与 SomeExcludeException 时，记录成失败调用
    @JProfiler(jKey = "你自定定义 key", mState = {JProEnum.TP, JProEnum.FunctionError})
    public void annotation() {
        
    }
    
    /**
    * 通过代码侵入方式使用ump
    */
    public void umpProfile() {
        CallerInfo info = null;
        try {
            info = Profiler.registerInfo("你自己定义 key");
            // do some business
        } catch (Exception e){
            // 如果需要标记本次埋点为失败调用，就调用下面这行
            Profiler.functionError(info);
        } finally {
            // 这行必须有，否则前面几行无效
            Profiler.registerInfoEnd(info);
        }
    }

}