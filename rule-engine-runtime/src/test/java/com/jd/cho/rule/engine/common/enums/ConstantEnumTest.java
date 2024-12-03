package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantEnumTest {
    @Test
    void test_getByCode() {
        for (ConstantEnum factorType : ConstantEnum.values()) {
            Assertions.assertThat(ConstantEnum.getByCode(factorType.getCode())).isEqualTo(factorType);
        }

        Assertions.assertThat(ConstantEnum.getByCode("notExist")).isNull();
    }
}