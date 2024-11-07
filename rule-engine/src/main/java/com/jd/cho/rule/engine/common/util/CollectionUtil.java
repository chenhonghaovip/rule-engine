package com.jd.cho.rule.engine.common.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class CollectionUtil {

    public static boolean containAnyOne(Collection<Object> all, Collection<Object> list) {
        if (CollectionUtils.isNotEmpty(all) && CollectionUtils.isNotEmpty(list)) {
            HashSet<String> ts = all.stream().map(Object::toString).collect(Collectors.toCollection(HashSet::new));
            return list.stream().anyMatch(ts::contains);
        }
        return false;
    }

    public static boolean containsAll(Collection<Object> all, Collection<Object> list) {
        if (CollectionUtils.isNotEmpty(all) && CollectionUtils.isNotEmpty(list)) {
            return new HashSet<>(all).containsAll(list);
        }
        return false;
    }
}
