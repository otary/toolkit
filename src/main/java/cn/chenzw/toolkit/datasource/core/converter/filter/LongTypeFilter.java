package cn.chenzw.toolkit.datasource.core.converter.filter;

import cn.chenzw.toolkit.datasource.core.converter.TypeFilter;

public class LongTypeFilter implements TypeFilter {

    @Override
    public boolean isMatch(String typeName, Integer size, Integer digits) {
        return true;
    }
}
