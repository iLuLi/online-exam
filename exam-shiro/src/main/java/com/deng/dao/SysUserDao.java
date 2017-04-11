package com.deng.dao;

import com.deng.entity.SysUser;

/**
 * Created by hp on 2017/4/10.
 */
public interface SysUserDao {
    SysUser queryByUserName(String username);
}
