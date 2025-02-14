package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ExpressOperationEnumTest {

    @Test
    void test_getOperationByGroup() {
        ExpressOperationEnum[] enums = ExpressOperationEnum.values();
        for (ExpressOperationEnum expressOperation : enums) {
            String group = expressOperation.getGroup();
            List<ExpressOperationEnum> operations = ExpressOperationEnum.getOperationByGroup(group);
            Assertions.assertThat(operations).isNotEmpty();
        }

        List<ExpressOperationEnum> operations = ExpressOperationEnum.getOperationByGroup("not exist group");
        Assertions.assertThat(operations).isEmpty();
    }

    @Test
    void test_getByCode() {
        ExpressOperationEnum[] enums = ExpressOperationEnum.values();
        for (ExpressOperationEnum expressOperation : enums) {
            String operator = expressOperation.getOperator();
            ExpressOperationEnum operation = ExpressOperationEnum.getByCode(operator);
            Assertions.assertThat(operation.getOperator()).isEqualTo(operator);
        }

        ExpressOperationEnum operation = ExpressOperationEnum.getByCode("not exist group");
        Assertions.assertThat(operation).isNull();
    }
}