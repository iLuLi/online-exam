package com.deng.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
public interface SysGenerateDao {

    List<Map<String, String>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String name);

    List<Map<String, String>> queryColumns(String name);

    int queryTotal(Map<String, Object> map);
}
