package com.jd.cho.rule.engine.factor.dto;

import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactorTypeDTO {

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 支持的操作符列表
     */
    private List<ComparativeOperator> comparativeOperatorList;
}
