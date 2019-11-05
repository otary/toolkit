package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * org.apache.commons.lang3.time.DateUtils扩展类
 *
 * @author chenzw
 */
public class DateExtUtils {

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

}
