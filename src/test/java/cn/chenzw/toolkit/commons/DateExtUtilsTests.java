package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@RunWith(JUnit4.class)
public class DateExtUtilsTests {


    @Test
    public void testRandom() {
        Date randomDate = DateExtUtils.random();

        System.out.println("随机生成日期:" + DateFormatUtils.format(randomDate, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testRandom2() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2009, 10, 10);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 10, 10);

        Date randomDate = DateExtUtils.random(startDate.getTime(), endDate.getTime());
        System.out.println(
                "随机生成日期(2009-10-10 ~ 2019-10-10):" + DateFormatUtils.format(randomDate, "yyyy-MM-dd HH:mm:ss"));
    }


    @Test
    public void testGetFirstDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 1, 0, 0, 0); // 月份值要减1
        calendar.set(Calendar.MILLISECOND, 0);

        Date firstDayOfMonth = DateExtUtils.getFirstDayOfMonth("2019-03", "yyyy-MM");
        Assert.assertEquals(calendar.getTime().getTime(), firstDayOfMonth.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 2, 20);
        Date firstDayOfMonth2 = DateExtUtils.getFirstDayOfMonth(calendar2.getTime());
        Assert.assertEquals(calendar.getTime().getTime(), firstDayOfMonth2.getTime());

    }

    @Test
    public void testGetLastDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31, 0, 0, 0); // 月份值要减1
        calendar.set(Calendar.MILLISECOND, 0);

        Date lastDayOfMonth = DateExtUtils.getLastDayOfMonth("2019-03", "yyyy-MM");
        Assert.assertEquals(calendar.getTime().getTime(), lastDayOfMonth.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 2, 20);
        Date lastDayOfMonth2 = DateExtUtils.getLastDayOfMonth(calendar2.getTime());
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

        boolean dayBetween = DateExtUtils
                .isDayBetween(calendar.getTime(), startDateCalendar.getTime(), endDateCalendar.getTime());
        Assert.assertTrue(dayBetween);

    }

    @Test
    public void testParseDate() throws ParseException {
        Date date = DateExtUtils.parseDate("2018-10-20 12:2:1");
        Assert.assertEquals("Sat Oct 20 12:02:01 CST 2018", date.toString());

        Date date2 = DateExtUtils.parseDate("2018-10-20");
        Assert.assertEquals("Sat Oct 20 00:00:00 CST 2018", date2.toString());

        Date date3 = DateExtUtils.parseDate("22:11:33");
        Assert.assertEquals("Thu Jan 01 22:11:33 CST 1970", date3.toString());

    }

    @Test
    public void testEraseTime(){
        Date date = DateExtUtils.eraseTime(new Date());
        Assert.assertEquals(0, date.getHours());
        Assert.assertEquals(0, date.getMinutes());
        Assert.assertEquals(0, date.getSeconds());
    }

}
