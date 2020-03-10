package cn.chenzw.toolkit.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzw
 */
public class HBaseUtils {

    private static Logger logger = LoggerFactory.getLogger(HBaseUtils.class);

    /**
     * 创建表
     *
     * @param configuration
     * @param tableName      表名
     * @param columnFamily   列族名
     * @param deleteIfExists 表已存在时是否先删除再创建
     * @return
     * @throws IOException
     */
    public static boolean createTable(Configuration configuration, String tableName, List<String> columnFamily, boolean deleteIfExists) throws IOException {
        Connection connection = HBaseConnectionHolder.get(configuration);
        Admin admin = connection.getAdmin();

        TableName name = TableName.valueOf(tableName);

        if (admin.tableExists(name)) {
            logger.debug("Htable [{}] already exists!", tableName);
            if (deleteIfExists) {
                admin.disableTable(name);
                admin.deleteTable(name);

                logger.debug("Delete htable [{}]!", tableName);
            } else {
                return false;
            }
        }
        admin.createTable(createTableDescriptor(name, columnFamily));

        return true;
    }

    public List<String> listAllTableNames() {
        return null;
    }


    private static TableDescriptor createTableDescriptor(TableName name, List<String> columnFamily) {
        List<ColumnFamilyDescriptor> familyDescriptors = new ArrayList<>(columnFamily.size());
        columnFamily.forEach(cf -> {
            familyDescriptors.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build());
        });
        return TableDescriptorBuilder.newBuilder(name)
                .setColumnFamilies(familyDescriptors)
                .build();
    }


}
