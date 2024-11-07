package com.jd.cho.rule.engine.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class BaseEntity {


    /**
     * 是否删除 1-否，0-是
     */
    private Boolean yn;

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
