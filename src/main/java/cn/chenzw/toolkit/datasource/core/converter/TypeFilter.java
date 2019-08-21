package cn.chenzw.toolkit.datasource.core.converter;

public interface TypeFilter {

    boolean isMatch(String typeName, Integer size, Integer digits);
}
