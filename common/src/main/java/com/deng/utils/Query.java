package com.deng.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
public class Query extends LinkedHashMap<String, Object> {
    //当前的页码
    private int page;
    //每页的条数
    private int limit;

    public Query(Map<String, Object> map) {
        this.page = Integer.parseInt(map.get("page").toString());
        this.limit = Integer.parseInt(map.get("limit").toString());

        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
