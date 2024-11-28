package com.jd.cho.rule.engine.common.util;


import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.exceptions.CommonException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class AssertUtil {

    public static void isTrue(boolean expression, CommonException commonError) {
        if (!expression) {
            throw new BusinessException(commonError);
        }
    }

    public static void isFalse(boolean expression, CommonException commonError) {
        if (expression) {
            throw new BusinessException(commonError);
        }
    }

    public static void isNull(Object object, CommonException commonError) {
        if (object != null) {
            throw new BusinessException(commonError);
        }
    }

    public static void isNotNull(Object object, CommonException commonError) {
        if (object == null) {
            throw new BusinessException(commonError);
        }
    }

    public static void isNotNull(Object object) {
        if (object == null) {
            throw new BusinessException();
        }
    }

    public static void isNotEmpty(Collection<?> collection, CommonException commonError) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(commonError);
        }
    }

    public static void isNotBlank(String str, CommonException commonError) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(commonError);
        }
    }

    public static void isNotBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException();
        }
    }


}
