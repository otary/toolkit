package cn.chenzw.toolkit.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class HBaseUtilsTests {

    @Test
    @Ignore
    public void testCreateTable() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.client.retries.number", "10");
        conf.set("hbase.client.pause", "1000");
        conf.set("zookeeper.recoverty.retry", "10");

        HBaseUtils.createTable(conf, "test", Arrays.asList("ss"), false);
    }
}
