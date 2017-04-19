package com.deng.service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
public interface SysGenerateService {

    List<Map<String, String>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String name);

    List<Map<String, String>> queryColumns(String name);

    byte[] generateCode(String[] tableNames);
}
