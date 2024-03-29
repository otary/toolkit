package cn.chenzw.toolkit.core.lang;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

@RunWith(JUnit4.class)
public class RuntimeKitTests {

    @Test
    public void testExecForStr() throws IOException {
        if (SystemUtils.IS_OS_WINDOWS) {
            String ipconfigStr = RuntimeKit.execForStr("ipconfig /all");
            Assert.assertTrue(StringUtils.contains(ipconfigStr, "IPv4 地址"));

            String ipconfigStr2 = RuntimeKit.execForStr("ipconfig", "/all");
            Assert.assertTrue(StringUtils.contains(ipconfigStr2, "IPv4 地址"));
        }

    }

    @Test
    public void testExecForLines() throws IOException {
        if (SystemUtils.IS_OS_WINDOWS) {
            List<String> ipConfigs = RuntimeKit.execForLines("ipconfig /all");
            Assert.assertTrue(ipConfigs.size() > 0);

            List<String> ipConfigs2 = RuntimeKit.execForLines("ipconfig", "/all");
            Assert.assertTrue(ipConfigs2.size() > 0);
        } else {
            List<String> homeDirs = RuntimeKit.execForLines("cd /home; mkdir -p test; ls; rm -rf test");
            Assert.assertTrue(homeDirs.contains("test"));
        }
    }

}
