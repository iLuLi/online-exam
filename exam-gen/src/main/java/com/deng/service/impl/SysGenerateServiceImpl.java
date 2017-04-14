package com.deng.service.impl;

import com.deng.dao.SysGenerateDao;
import com.deng.service.SysGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
@Service("sysGenerateService")
public class SysGenerateServiceImpl implements SysGenerateService {
    @Autowired
    private SysGenerateDao sysGenerateDao;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGenerateDao.queryList(map);
    }

    @Override
    public int queryTotal() {
        return sysGenerateDao.queryTotal();
    }

    @Override
    public Map<String, Object> queryTable(String name) {
        return sysGenerateDao.queryTable(name);
    }

    @Override
    public List<Map<String, Object>> queryColumns(String name) {
        return sysGenerateDao.queryColumns(name);
    }
}
