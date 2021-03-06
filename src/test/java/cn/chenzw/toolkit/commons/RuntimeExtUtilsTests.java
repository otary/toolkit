package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

@RunWith(JUnit4.class)
public class RuntimeExtUtilsTests {

    @Test
    public void testExecForStr() throws IOException {
        String ipconfigStr = RuntimeExtUtils.execForStr("ipconfig /all");
        Assert.assertTrue(StringUtils.contains(ipconfigStr, "IPv4 地址"));

        String ipconfigStr2 = RuntimeExtUtils.execForStr("ipconfig", "/all");
        Assert.assertTrue(StringUtils.contains(ipconfigStr2, "IPv4 地址"));
    }

    @Test
    public void testExecForLines() throws IOException {
        List<String> ipconfigs = RuntimeExtUtils.execForLines("ipconfig /all");
        Assert.assertTrue(ipconfigs.size() > 0);

        List<String> ipconfigs2 = RuntimeExtUtils.execForLines("ipconfig", "/all");
        Assert.assertTrue(ipconfigs2.size() > 0);
    }



}
