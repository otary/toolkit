package cn.chenzw.toolkit.datasource.core.converter;

/**
 * jdbc类型与jdbc类型映射
 *
 * @author chenzw
 */
public class JdbcTypeMapping {

    private String jdbcType;
    private Class<?> javaType;

    /**
     * 类型匹配器
     */
    private JavaTypeFilter javaTypeFilter;

    public JdbcTypeMapping(String jdbcType, Class<?> javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public JdbcTypeMapping(String jdbcType, Class<?> javaType, JavaTypeFilter javaTypeFilter) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.javaTypeFilter = javaTypeFilter;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public JavaTypeFilter getJavaTypeFilter() {
        return javaTypeFilter;
    }

    public void setJavaTypeFilter(JavaTypeFilter javaTypeFilter) {
        this.javaTypeFilter = javaTypeFilter;
    }
}
