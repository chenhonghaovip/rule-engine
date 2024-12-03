package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FactorTypeEnumTest {
    @Test
    void test_getByCode() {
        for (FactorTypeEnum factorType : FactorTypeEnum.values()) {
            Assertions.assertThat(FactorTypeEnum.getByCode(factorType.getCode())).isEqualTo(factorType);
        }

        Assertions.assertThat(FactorTypeEnum.getByCode("notExist")).isNull();
    }

}