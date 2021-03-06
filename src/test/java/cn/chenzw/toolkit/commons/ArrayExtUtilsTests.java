package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class ArrayExtUtilsTests {


    @Test
    public void testSplit() {
        String[] data = new String[]{"1000000008334", "1000000008333", "1000000008332", "1000000008331", "1000000008330", "1000000008329", "1000000008328"};

        List<String[]> result = ArrayExtUtils.split(data, 2);

        Assert.assertEquals(result.size(), 4);
    }

    @Test
    public void testClone(){
        String[] data = {"张三", "李四", "王五"};
        String[] clone = ArrayExtUtils.clone(data);

        Assert.assertEquals("[张三, 李四, 王五]", Arrays.toString(clone));
        Assert.assertNotEquals(data.hashCode(), clone.hashCode());
    }
}
