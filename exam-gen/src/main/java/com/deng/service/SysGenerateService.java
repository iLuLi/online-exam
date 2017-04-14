package com.deng.service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
public interface SysGenerateService {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal();

    Map<String, Object> queryTable(String name);

    List<Map<String, Object>> queryColumns(String name);
}
