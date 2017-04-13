package com.deng.utils;

import java.util.List;

/**
 * Created by hp on 2017/4/13.
 */
public class PageUtils {
    private int curPage;
    private int pageSize;
    private int totalCount;
    private int pageCount;
    private List<?> list;

    public PageUtils(int curPage, int pageSize, int totalCount, List<?> list) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageCount = (int) Math.ceil((double) totalCount / pageSize);
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
