package com.deng.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
public interface SysGenerateDao {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    Map<String, Object> queryTable(String name);

    List<Map<String, Object>> queryColumns(String name);

    int queryTotal();
}
