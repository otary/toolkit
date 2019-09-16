package cn.chenzw.toolkit.datasource.core;

public class PageUtils {

    private PageUtils() {
    }


    /**
     * 计算总页数
     *
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @return 总页数
     */
    public static int countTotalPage(final int totalCount, final int pageSize) {
        if (totalCount == 0) {
            return 1;
        }
        if (totalCount % pageSize == 0) {
            // 刚好整除
            return totalCount / pageSize;
        } else {
            // 不能整除则总页数 + 1
            return totalCount / pageSize + 1;
        }
    }

    /**
     * 计算当前分页的起始记录索引
     *
     * @param offset   当前第几页
     * @param pageSize 每页记录数
     * @return 当前页的起始记录索引
     */
    public static int countStartOffset(final int offset, final int pageSize) {
        return (offset - 1) * pageSize;
    }

}
