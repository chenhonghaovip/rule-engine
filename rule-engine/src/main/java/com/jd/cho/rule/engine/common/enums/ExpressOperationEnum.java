package com.jd.cho.rule.engine.common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public enum ExpressOperationEnum {
    /**
     * 文本类型
     */
    TEXT_NULL(FactorTypeEnum.TEXT.getCode(), "TEXT_NULL", "isBlank(%s)", "为空"),
    TEXT_NOT_NULL(FactorTypeEnum.TEXT.getCode(), "TEXT_NOT_NULL", "isNotBlank(%s)", "不为空"),
    TEXT_EQUAL(FactorTypeEnum.TEXT.getCode(), "TEXT_EQUAL", "%s == %s", "等于"),
    TEXT_UNEQUAL(FactorTypeEnum.TEXT.getCode(), "TEXT_UNEQUAL", "%s != %s", "不等于"),
    TEXT_LIKE(FactorTypeEnum.TEXT.getCode(), "TEXT_LIKE", "%s like %s", "包含"),

    /**
     * 数值类型
     */
    NUM_GREATER_THAN(FactorTypeEnum.NUM.getCode(), "NUM_GREATER_THAN", "%s > %s", "大于"),
    NUM_GREATER_THAN_EQUAL(FactorTypeEnum.NUM.getCode(), "NUM_GREATER_THAN_EQUAL", "%s >= %s", "大于等于"),
    NUM_LESS_THAN(FactorTypeEnum.NUM.getCode(), "NUM_LESS_THAN", "%s < %s", "小于"),
    NUM_LESS_THAN_EQUAL(FactorTypeEnum.NUM.getCode(), "NUM_LESS_THAN_EQUAL", "%s <= %s", "小于等于"),
    NUM_EQUAL(FactorTypeEnum.NUM.getCode(), "NUM_EQUAL", "%s == %s", "等于"),
    NUM_UNEQUAL(FactorTypeEnum.NUM.getCode(), "NUM_UNEQUAL", "%s != %s", "不等于"),
    NUM_IS_NULL(FactorTypeEnum.NUM.getCode(), "NUM_IS_NULL", "%s == null", "为空"),
    NUM_NOT_NULL(FactorTypeEnum.NUM.getCode(), "NUM_NOT_NULL", "%s != null", "不为空"),

    /**
     * 日期类型
     */
    DATE_BEFORE(FactorTypeEnum.DATE.getCode(), "DATE_BEFORE", "dateBefore(%s,%s)", "早于"),
    DATE_AFTER(FactorTypeEnum.DATE.getCode(), "DATE_AFTER", "dateAfter(%s,%s)", "晚于"),
    DATE_EQUAL_DAY(FactorTypeEnum.DATE.getCode(), "DATE_EQUAL_DAY", "dateEqualDay(%s,%s)", "等于(天)"),
    DATE_EQUAL(FactorTypeEnum.DATE.getCode(), "DATE_EQUAL", "dateEqual(%s,%s)", "等于"),
    DATE_IS_NULL(FactorTypeEnum.DATE.getCode(), "DATE_IS_NULL", "%s == null", "为空"),
    DATE_NOT_NULL(FactorTypeEnum.DATE.getCode(), "DATE_NOT_NULL", "%s != null", "不为空"),


    /**
     * 集合类型
     */
    COLLECTION_IS_NULL(FactorTypeEnum.COLLECTION.getCode(), "COLLECTION_IS_NULL", "isEmpty(%s)", "为空"),
    COLLECTION_NOT_NULL(FactorTypeEnum.COLLECTION.getCode(), "COLLECTION_NOT_NULL", "isNotEmpty(%s)", "不为空"),
    COLLECTION_CONTAIN_ANY_ONE(FactorTypeEnum.COLLECTION.getCode(), "COLLECTION_CONTAIN_ANY_ONE", "containAnyOne(%s,%s)", "包含任意"),
    COLLECTION_CONTAIN_ALL(FactorTypeEnum.COLLECTION.getCode(), "COLLECTION_CONTAIN_ALL", "containsAll(%s,%s)", "包含全部"),


    BOOLEAN_IS_TRUE(FactorTypeEnum.BOOLEAN.getCode(), "BOOLEAN_IS_TRUE", "Boolean.TRUE.equals(s%)", "true"),
    BOOLEAN_IS_FALSE(FactorTypeEnum.BOOLEAN.getCode(), "BOOLEAN_IS_FALSE", "Boolean.FALSE.equals(s%)", "false"),
    ;


    private final String group;
    private final String operator;
    private final String expression;
    private final String remark;

    ExpressOperationEnum(String group, String operator, String expression, String remark) {
        this.group = group;
        this.operator = operator;
        this.expression = expression;
        this.remark = remark;
    }

    public static final Map<String, List<ExpressOperationEnum>> MAP = Arrays.stream(ExpressOperationEnum.values()).collect(Collectors.groupingBy(ExpressOperationEnum::getGroup));

    public static ExpressOperationEnum getOperationByOperator(String operator) {

        ExpressOperationEnum[] list = ExpressOperationEnum.values();
        for (ExpressOperationEnum item : list) {
            String compareOperator = item.getOperator();
            if (compareOperator.equals(operator)) {
                return item;
            }
        }
        return null;
    }

    public String getOperator() {
        return operator;
    }

    public String getExpression() {
        return expression;
    }

    public String getRemark() {
        return remark;
    }

    public String getGroup() {
        return group;
    }
}
