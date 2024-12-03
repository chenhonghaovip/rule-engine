package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VarTypeEnumTest {
    @Test
    void test_getByCode() {
        VarTypeEnum[] enums = VarTypeEnum.values();
        for (VarTypeEnum type : enums) {
            Assertions.assertThat(VarTypeEnum.getByCode(type.getCode())).isEqualTo(type);
        }

        Assertions.assertThat(VarTypeEnum.getByCode("notExist")).isNull();
    }
}