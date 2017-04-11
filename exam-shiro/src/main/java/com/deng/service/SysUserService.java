package com.deng.service;

import com.deng.entity.SysUser;

/**
 * Created by hp on 2017/4/10.
 */
public interface SysUserService {
    SysUser queryByUserName(String username);
}
