package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * org.apache.commons.lang3.time.DateUtils扩展类
 *
 * @author chenzw
 */
public class DateExtUtils {

    /**
     * java.util.Date EEE MMM zzz 缩写数组
     */
    private final static String[] wtb = { //
            "sun", "mon", "tue", "wed", "thu", "fri", "sat", // 星期
            "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec", //
            "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"//
    };

    /**
     * 常用的UTC时间格式
     */
    private final static String[] FREQUENTLEY_USED_UTC_WITH_Z_DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    };

    private final static String[] FREQUENTLEY_USED_UTC_DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    };

    /**
     * 常用纯数字时间格式
     */
    private final static String[] FREQUENTLY_USED_NUMBER_DATE_FORMATS = new String[]{
            "yyyyMMddHHmmss",
            "yyyyMMddHHmmssSSS",
            "yyyyMMdd",
            "yyyyMMss",
            "HHmmss"
    };

    /**
     * CST格式
     */
    private final static String[] FREQUENTLY_USED_CST_DATE_FORMATS = new String[]{
            "EEE, dd MMM yyyy HH:mm:ss z",
            "EEE MMM dd HH:mm:ss zzz yyyy"
    };

    /**
     * 常用的时间格式
     */
    private final static String[] FREQUENTLY_USED_DATE_FORMATS = new String[]{
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy.MM.dd HH:mm:ss",
            "yyyy年MM月dd日 HH时mm分ss秒",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "yyyy.MM.dd",
            "HH:mm:ss",
            "HH时mm分ss秒",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss.SSS"

    };


    /*
    private final static Map<Integer, List<String>> FREQUENTLEY_USED_UTC_WITH_Z_DATE_FORMAT_MAP;
    private final static Map<Integer, List<String>> FREQUENTLEY_USED_UTC_DATE_FORMAT_MAP;
    private final static Map<Integer, List<String>> FREQUENTLY_USED_NUMBER_DATE_FORMAT_MAP;
    private final static Map<Integer, List<String>> FREQUENTLY_USED_DATE_FORMAT_MAP;
    private final static Map<Integer, List<String>> FREQUENTLY_USED_CST_DATE_FORMAT_MAP;
    */


    static {
        /*
        FREQUENTLEY_USED_UTC_WITH_Z_DATE_FORMAT_MAP = Arrays.stream(FREQUENTLEY_USED_UTC_WITH_Z_DATE_FORMATS).collect(Collectors.groupingBy((s) -> s.length()));
        FREQUENTLEY_USED_UTC_DATE_FORMAT_MAP = Arrays.stream(FREQUENTLEY_USED_UTC_DATE_FORMATS).collect(Collectors.groupingBy((s) -> s.length()));
        FREQUENTLY_USED_NUMBER_DATE_FORMAT_MAP = Arrays.stream(FREQUENTLY_USED_NUMBER_DATE_FORMATS).collect(Collectors.groupingBy((s) -> s.length()));
        FREQUENTLY_USED_CST_DATE_FORMAT_MAP = Arrays.stream(FREQUENTLY_USED_CST_DATE_FORMATS).collect(Collectors.groupingBy((s) -> s.length()));
        FREQUENTLY_USED_DATE_FORMAT_MAP = Arrays.stream(FREQUENTLY_USED_DATE_FORMATS).collect(Collectors.groupingBy((s) -> s.length()));
        */
    }


    private DateExtUtils() {
    }


    /**
     * 随机生成日期（startDateInclusive至endDateExclusive）
     *
     * @param startDateInclusive
     * @param endDateExclusive
     * @return
     */
    public static final Date random(Date startDateInclusive, Date endDateExclusive) {
        int betweenSeconds = (int) ((endDateExclusive.getTime() - startDateInclusive.getTime()) / 1000);

        Calendar instance = Calendar.getInstance();
        instance.setTime(startDateInclusive);
        instance.add(Calendar.SECOND, RandomUtils.nextInt(0, betweenSeconds));
        return instance.getTime();
    }

    /**
     * 随机生成日期
     *
     * @return
     */
    public static final Date random() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(0L);
        instance.add(Calendar.MINUTE, RandomUtils.nextInt(0, Integer.MAX_VALUE));
        return instance.getTime();
    }

    /**
     * 获取当月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取当月的第一天
     *
     * @param month
     * @param format 日期格式
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfMonth(String month, String format) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate(month, format));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    /**
     * 获取指定日期当月的最后一天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定月份的最后一天
     *
     * @param month
     * @param format 日期格式
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfMonth(String month, String format) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate(month, format));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 判断指定日期是否在某个时间区间内
     *
     * @param date
     * @param startDateInclusive
     * @param endDateInclusive
     * @return
     */
    public static boolean isDayBetween(Date date, Date startDateInclusive, Date endDateInclusive) {
        if (date == null) {
            return false;
        }
        return ((date.after(startDateInclusive) && date.before(endDateInclusive)) || date.equals(startDateInclusive)
                || date.equals(endDateInclusive));
    }


    /**
     * 自动解析日期
     *
     * @param dateTimeStr
     * @return
     */
    public static Date parseDate(String dateTimeStr) throws ParseException {
        if (StringUtils.isEmpty(dateTimeStr)) {
            throw new IllegalArgumentException("Date string must not be null");
        }

        int dateTimeLen = dateTimeStr.length();
        String[] dateFormats;

        if (NumberUtils.isCreatable(dateTimeStr)) {
            // 纯数字
            dateFormats = FREQUENTLY_USED_NUMBER_DATE_FORMATS;
        } else if (StringUtils.contains(dateTimeStr, 'T')) {
            // UTC
            if (StringUtils.contains(dateTimeStr, 'Z')) {
                dateFormats = FREQUENTLEY_USED_UTC_WITH_Z_DATE_FORMATS;
            } else {
                dateFormats = FREQUENTLEY_USED_UTC_DATE_FORMATS;
            }
        } else if (StringUtils.containsAny(dateTimeStr, wtb)) {
            // CST格式
            dateFormats = FREQUENTLY_USED_CST_DATE_FORMATS;
        } else {
            // 其它
            dateFormats = FREQUENTLY_USED_DATE_FORMATS;
        }
        return DateUtils.parseDate(dateTimeStr, dateFormats);
    }

    /**
     * Date => {@link Instant}
     *
     * @param date
     * @return {@link Instant}对象
     */
    public static Instant toInstant(Date date) {
        return date == null ? null : date.toInstant();
    }


    /**
     * {@link TemporalAccessor} => {@link Instant}
     *
     * @param temporalAccessor
     * @return {@link Instant}对象
     */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        if (temporalAccessor == null) {
            return null;
        }
        Instant result;
        if (temporalAccessor instanceof Instant) {
            result = (Instant) temporalAccessor;
        } else if (temporalAccessor instanceof LocalDateTime) {
            result = ((LocalDateTime) temporalAccessor).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof ZonedDateTime) {
            result = ((ZonedDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof OffsetDateTime) {
            result = ((OffsetDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof LocalDate) {
            result = ((LocalDate) temporalAccessor).atStartOfDay(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof LocalTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((LocalTime) temporalAccessor).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof OffsetTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toInstant();
        } else {
            result = Instant.from(temporalAccessor);
        }

        return result;
    }


}
