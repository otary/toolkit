package cn.chenzw.toolkit.datasource.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PageUtilsTests {

    @Test
    public void testCountTotalPage() {
        Assert.assertEquals(7, PageUtils.countTotalPage(100, 15));
    }

    @Test
    public void testCountStartOffset(){
        Assert.assertEquals(27, PageUtils.countStartOffset(10, 3));
    }


}
