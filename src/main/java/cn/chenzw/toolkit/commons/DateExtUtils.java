package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.RandomUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * org.apache.commons.lang3.time.DateUtils扩展类
 * @author chenzw
 */
public class DateExtUtils {

    private static final int MILLISECOND_PER_DAY = 24 * 60 * 60 * 1000;


    /**
     * 随机生成日期（startDateInclusive至endDateExclusive）
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
     * @return
     */
    public static final Date random() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(0L);
        instance.add(Calendar.MINUTE, RandomUtils.nextInt(0, Integer.MAX_VALUE));
        return instance.getTime();
    }


}
