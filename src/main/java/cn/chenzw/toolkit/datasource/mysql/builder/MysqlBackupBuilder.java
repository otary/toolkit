package cn.chenzw.toolkit.datasource.mysql.builder;


/**
 * 备份构建器
 *
 * @author chenzw
 */
public class MysqlBackupBuilder {

    private String host;
    private Integer port;
    private String userName;
    private String password;
    private String[] databaseNames;
    private String[] tableNames;

    private MysqlBackupBuilder(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.userName = builder.userName;
        this.password = builder.password;
        this.databaseNames = builder.databaseNames;
        this.tableNames = builder.tableNames;
    }


    public void build() {

    }

    public static class Builder {
        private String host;
        private Integer port;
        private String userName;
        private String password;
        private String[] databaseNames;
        private String[] tableNames;


        public void host(String host) {
            this.host = host;
        }

        public void port(Integer port) {
            this.port = port;
        }

        public void userName(String userName) {
            this.userName = userName;
        }

        public void password(String password) {
            this.password = password;
        }

        public void databaseNames(String... databaseNames) {
            this.databaseNames = databaseNames;
        }

        public void tableNames(String... tableNames) {
            this.tableNames = tableNames;
        }


        public MysqlBackupBuilder build() {
            return new MysqlBackupBuilder(this);
        }


    }

}
