package cn.chenzw.toolkit.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenzw
 */
public class HBaseConnectionHolder {

    private static Map<Configuration, Connection> connectionMap = new ConcurrentHashMap<>();

    public static Connection get(Configuration configuration) {
        if (connectionMap.containsKey(configuration)) {
            return connectionMap.get(configuration);
        }

        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionMap.put(configuration, connection);
        return connection;
    }

}
