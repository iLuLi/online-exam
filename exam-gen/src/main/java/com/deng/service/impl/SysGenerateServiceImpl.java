package com.deng.service.impl;

import com.deng.dao.SysGenerateDao;
import com.deng.service.SysGenerateService;
import com.deng.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by hp on 2017/4/13.
 */
@Service("sysGenerateService")
public class SysGenerateServiceImpl implements SysGenerateService {
    @Autowired
    private SysGenerateDao sysGenerateDao;

    @Override
    public List<Map<String, String>> queryList(Map<String, Object> map) {
        return sysGenerateDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysGenerateDao.queryTotal(map);
    }

    @Override
    public Map<String, String> queryTable(String name) {
        return sysGenerateDao.queryTable(name);
    }

    @Override
    public List<Map<String, String>> queryColumns(String name) {
        return sysGenerateDao.queryColumns(name);
    }

    @Override
    public byte[] generateCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generateCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
