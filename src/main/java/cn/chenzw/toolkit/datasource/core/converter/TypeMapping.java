package cn.chenzw.toolkit.datasource.core.converter;

/**
 * jdbc类型与jdbc类型映射
 *
 * @author chenzw
 */
public class TypeMapping {

    private String jdbcType;
    private Class<?> javaType;

    /**
     * 类型匹配器
     */
    private TypeFilter typeFilter;

    public TypeMapping(String jdbcType, Class<?> javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public TypeMapping(String jdbcType, Class<?> javaType, TypeFilter typeFilter) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.typeFilter = typeFilter;
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

    public TypeFilter getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(TypeFilter typeFilter) {
        this.typeFilter = typeFilter;
    }
}
