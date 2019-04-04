package cn.zxhysy.booksmall.utils;

import java.util.List;

/**
 * @program: book_small
 * @className: MyPageInfo
 * @description: 与PageInfo相同对象
 * @author: zxh
 * @date: 2019-04-02 13:58
 */
public class MyPageInfo<T> {
    /** 当前页数 */
    private int pageNum;
    /** 总页数 */
    private int pages;
    /** 当前页数 */
    private int pageSize;
    /** 总记录数 */
    private long total;
    /** 每行显示的内容 */
    private List<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
