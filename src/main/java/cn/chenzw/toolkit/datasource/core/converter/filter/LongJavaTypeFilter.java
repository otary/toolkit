package cn.chenzw.toolkit.datasource.core.converter.filter;

import cn.chenzw.toolkit.datasource.core.converter.JavaTypeFilter;

public class LongJavaTypeFilter implements JavaTypeFilter {

    @Override
    public boolean isMatch(String typeName, Integer size, Integer digits) {
        return true;
    }
}
