package com.jd.cho.rule.engine.common.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RelationTypeEnumTest {
    @Test
    void test_getByCode() {
        for (RelationTypeEnum type : RelationTypeEnum.values()) {
            Assertions.assertThat(RelationTypeEnum.getByCode(type.getCode())).isEqualTo(type);
        }

        Assertions.assertThat(RelationTypeEnum.getByCode("notExist")).isNull();
    }
}