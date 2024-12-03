package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RulePackTypeEnumTest {
    @Test
    void test_getByCode() {
        RulePackTypeEnum[] enums = RulePackTypeEnum.values();
        for (RulePackTypeEnum type : enums) {
            Assertions.assertThat(RulePackTypeEnum.getByCode(type.getCode())).isEqualTo(type);
        }

        Assertions.assertThat(RulePackTypeEnum.getByCode("notExist")).isNull();
    }
}