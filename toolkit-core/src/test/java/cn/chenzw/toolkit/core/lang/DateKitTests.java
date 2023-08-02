package cn.chenzw.toolkit.core.lang;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(JUnit4.class)
public class DateKitTests {


    @Test
    public void testRandom() {
        Date randomDate = DateKit.random();
        log.info("随机生成日期 => {}", DateFormatUtils.format(randomDate, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testRandom2() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2009, 10, 10);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 10, 10);

        Date randomDate = DateKit.random(startDate.getTime(), endDate.getTime());
        log.info("随机生成日期(2009-10-10 ~ 2019-10-10) => {}", DateFormatUtils.format(randomDate, "yyyy-MM-dd HH:mm:ss"));
    }


    @Test
    public void testGetFirstDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 1, 0, 0, 0); // 月份值要减1
        calendar.set(Calendar.MILLISECOND, 0);

        Date firstDayOfMonth = DateKit.getFirstDayOfMonth("2019-03", "yyyy-MM");
        Assert.assertEquals(calendar.getTime().getTime(), firstDayOfMonth.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 2, 20);
        Date firstDayOfMonth2 = DateKit.getFirstDayOfMonth(calendar2.getTime());
        Assert.assertEquals(calendar.getTime().getTime(), firstDayOfMonth2.getTime());

    }

    @Test
    public void testGetLastDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31, 0, 0, 0); // 月份值要减1
        calendar.set(Calendar.MILLISECOND, 0);

        Date lastDayOfMonth = DateKit.getLastDayOfMonth("2019-03", "yyyy-MM");
        Assert.assertEquals(calendar.getTime().getTime(), lastDayOfMonth.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 2, 20);
        Date lastDayOfMonth2 = DateKit.getLastDayOfMonth(calendar2.getTime());
        Assert.assertEquals(calendar.getTime().getTime(), lastDayOfMonth2.getTime());
    }

    @Test
    public void testIsDayBetween() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 20);

        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(2019, 3, 1);

        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.set(2019, 3, 31);

        boolean dayBetween = DateKit.isDayBetween(calendar.getTime(), startDateCalendar.getTime(), endDateCalendar.getTime());
        Assert.assertTrue(dayBetween);
    }

    @Test
    public void testParseDate() throws ParseException {
        Date date = DateKit.parseDate("2018-10-20 12:2:1");
        Assert.assertEquals("Sat Oct 20 12:02:01 CST 2018", date.toString());

        Date date2 = DateKit.parseDate("2018-10-20");
        Assert.assertEquals("Sat Oct 20 00:00:00 CST 2018", date2.toString());

        Date date3 = DateKit.parseDate("22:11:33");
        Assert.assertEquals("Thu Jan 01 22:11:33 CST 1970", date3.toString());

    }

    @Test
    public void testEraseTime() {
        Date date = DateKit.eraseTime(new Date());
        Assert.assertEquals(0, date.getHours());
        Assert.assertEquals(0, date.getMinutes());
        Assert.assertEquals(0, date.getSeconds());
    }

    @Test
    public void testGetGapTime() {
        String gapTime = DateKit.getGapTime(106752, TimeUnit.MILLISECONDS);
        Assert.assertEquals("01:46", gapTime);
    }

    @Test
    public void testGetLastMonth() {
        Calendar instance = Calendar.getInstance();
        instance.set(2001, 9, 6);
        Date date = instance.getTime();

        Date lastMonth = DateKit.getLastMonth(date);

        Assert.assertEquals("2001-09-06", DateFormatUtils.format(lastMonth, "yyyy-MM-dd"));
    }

    @Test
    public void testGetNextMonth() {

    }
}
