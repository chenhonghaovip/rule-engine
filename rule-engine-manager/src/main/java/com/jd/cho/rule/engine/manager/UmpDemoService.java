package com.jd.cho.rule.engine.manager;

/**
* Description: Ump Demo
*
* @author DongBoot
* @see <a href="https://joyspace.jd.com/pages/EyinifVCoYBZKpEhcukj">Ump Starter使用说明</a>
* @see <a href="https://taishan.jd.com/ump/monitor">Ump管理端</a>
*/
public interface UmpDemoService {

    /**
    *  通过注解方式使用ump
    */
    void annotation();
    
    /**
    * 通过代码侵入方式使用ump
    */
    void umpProfile();

}