package cn.chenzw.toolkit.core.collection;

import cn.chenzw.toolkit.core.collection.ArrayKit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class ArrayKitTests {
    @Test
    public void testSplit() {
        String[] data = new String[]{"11", "22", "33", "44", "55", "66", "77"};

        List<String[]> result = ArrayKit.split(data, 2);

        Assert.assertEquals(result.size(), 4);
    }

    @Test
    public void testClone() {
        String[] data = {"张三", "李四", "王五"};
        String[] clone = ArrayKit.clone(data);

        Assert.assertEquals("[张三, 李四, 王五]", Arrays.toString(clone));
        Assert.assertNotEquals(data.hashCode(), clone.hashCode());
    }

    @Test
    public void testTrim() {
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "", "e"}, ArrayKit.trim(new String[]{" a", "b ", "c", "", "   e  "}));
    }
}
