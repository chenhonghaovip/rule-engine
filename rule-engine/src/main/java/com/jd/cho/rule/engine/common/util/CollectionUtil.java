package com.jd.cho.rule.engine.common.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class CollectionUtil {

    /**
     * 判断两个集合是否包含任意一个元素
     *
     * @param all  原始值
     * @param list 目标值
     * @param <T>  泛型
     * @return boolean
     */
    public static <T> boolean containAnyOne(Collection<T> all, Collection<T> list) {
        if (CollectionUtils.isNotEmpty(all) && CollectionUtils.isNotEmpty(list)) {
            Set<T> ts = new HashSet<>(all);
            return list.stream().anyMatch(ts::contains);
        }
        return false;
    }

    /**
     * 判断两个集合是否包含全部元素
     *
     * @param all  原始值
     * @param list 目标值
     * @param <T>  泛型
     * @return boolean
     */
    public static <T> boolean containsAll(Collection<T> all, Collection<T> list) {
        if (CollectionUtils.isNotEmpty(all) && CollectionUtils.isNotEmpty(list)) {
            return new HashSet<>(all).containsAll(list);
        }
        return false;
    }

    /**
     * 将数组类型转换为集合类型
     *
     * @param t   数组
     * @param <T> 泛型
     * @return 集合List
     */
    public static <T> List<T> changeArrayToList(T[] t) {
        return Arrays.stream(t).collect(Collectors.toList());
    }
}
