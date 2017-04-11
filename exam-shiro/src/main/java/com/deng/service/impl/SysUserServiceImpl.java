package com.deng.service.impl;

import com.deng.dao.SysUserDao;
import com.deng.entity.SysUser;
import com.deng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2017/4/10.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }
}
