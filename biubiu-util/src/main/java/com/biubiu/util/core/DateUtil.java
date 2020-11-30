package com.biubiu.util.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateUtil
 *
 * @author wbbaijq
 */
public class DateUtil {

    /**
     * 返回当前时间 字符串 默认：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return timeStr
     */
    public static String getCurrentTimeStr() {
        return getCurrentTimeStr(null);
    }

    /**
     * 返回当前时间 字符串 默认：yyyy-MM-dd HH:mm:ss.SSS 自定义格式
     *
     * @return timeStr
     */
    public static String getCurrentTimeStr(String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        return getCurrentTime().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 返回当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
