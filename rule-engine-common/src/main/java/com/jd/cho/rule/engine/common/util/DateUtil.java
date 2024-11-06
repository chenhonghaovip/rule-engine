package com.jd.cho.rule.engine.common.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 时间转换工具类
 *
 * @author liming356
 * @date 2020/3/17/017
 */
public class DateUtil {
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_DATE);

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String DATE_TIME_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";

    /**
     * 将string类型yyyy-MM-dd HH:mm:ss 转换为LocalDateTime类型
     *
     * @param date 时间
     * @return LocalDateTime
     */
    public static LocalDateTime convertLocalDateTime(String date) {
        if (StringUtils.isBlank(date)) {
            throw new RuntimeException("日期数据不能为空");
        }
        if (date.matches(DATE_PATTERN)) {
            date = date + " 00:00:01";
        }
        if (date.matches(DATE_TIME_PATTERN)) {
            return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        }
        throw new RuntimeException("日期格式无法识别");
    }

    /**
     * 对比日期时间
     *
     * @param beginDate 开始时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @param endDate   结束时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @return 对比结果
     */
    public static boolean dateBefore(String beginDate, String endDate) {
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = convertLocalDateTime(beginDate);
            LocalDateTime localEndDate = convertLocalDateTime(endDate);
            return localBeginDate.isBefore(localEndDate);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dateBefore(Date beginDate, Date endDate) {
        if (Objects.isNull(beginDate) || Objects.isNull(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localBeginDate.isBefore(localEndDate);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对比日期时间
     *
     * @param beginDate 开始时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @param endDate   结束时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @return 对比结果
     */
    public static boolean dateAfter(String beginDate, String endDate) {
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = convertLocalDateTime(beginDate);
            LocalDateTime localEndDate = convertLocalDateTime(endDate);
            return localBeginDate.isAfter(localEndDate);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 对比日期时间
     *
     * @param beginDate 开始时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @param endDate   结束时间yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd
     * @return 对比结果
     */
    public static boolean dateAfter(Date beginDate, Date endDate) {
        if (Objects.isNull(beginDate) || Objects.isNull(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localBeginDate.isAfter(localEndDate);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param beginDate 开始日期字符串
     * @param endDate   结束日期字符串
     * @return 如果两个日期都是非空的，并且它们是同一天，则返回true；否则返回false
     */
    public static boolean dateEqualDay(String beginDate, String endDate) {
        // 检查日期字符串是否为空，如果任意一个为空，则返回false
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = convertLocalDateTime(beginDate);
            LocalDateTime localEndDate = convertLocalDateTime(endDate);
            return localBeginDate.toLocalDate().equals(localEndDate.toLocalDate());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param beginDate 开始日期字符串
     * @param endDate   结束日期字符串
     * @return 如果两个日期都是非空的，并且它们是同一天，则返回true；否则返回false
     */
    public static boolean dateEqualDay(Date beginDate, Date endDate) {
        if (Objects.isNull(beginDate) || Objects.isNull(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localBeginDate.toLocalDate().equals(localEndDate.toLocalDate());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断两个日期字符串是否相等
     *
     * @param beginDate 开始日期字符串
     * @param endDate   结束日期字符串
     * @return 如果两个日期字符串相等，则返回true；否则返回false
     */
    public static boolean dateEqual(String beginDate, String endDate) {
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return false;
        }
        if (beginDate.matches(DATE_TIME_PATTERN) && endDate.matches(DATE_TIME_PATTERN)) {
            return Objects.equals(beginDate, endDate);
        }
        return false;
    }

    public static boolean dateEqual(Date beginDate, Date endDate) {
        if (Objects.isNull(beginDate) || Objects.isNull(endDate)) {
            return false;
        }
        try {
            LocalDateTime localBeginDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localBeginDate.equals(localEndDate);
        } catch (Exception e) {
            return false;
        }
    }

}
