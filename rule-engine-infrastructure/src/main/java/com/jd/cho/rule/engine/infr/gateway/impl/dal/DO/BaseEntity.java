package com.jd.cho.rule.engine.infr.gateway.impl.dal.DO;

import lombok.Data;

import java.util.Date;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class BaseEntity {


    /**
     * 是否删除 1-否，0-是
     */
    private Integer yn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 租户
     */
    private String tenant;
}
