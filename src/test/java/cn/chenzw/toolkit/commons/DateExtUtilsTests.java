package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
}
