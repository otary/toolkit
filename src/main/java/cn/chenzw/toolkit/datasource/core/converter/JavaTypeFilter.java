package cn.chenzw.toolkit.datasource.core.converter;

/**
 * 字段类型过滤器
 * @author chenzw
 */
public interface JavaTypeFilter {

    /**
     * 进行匹配
     * @param typeName 字段类型名称
     * @param size 字符长度
     * @param digits 精度
     * @return
     */
    boolean isMatch(String typeName, Integer size, Integer digits);
}
