package com.deng.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by hp on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jdbc.xml","classpath:mybatis.xml"})
public class SysUserDaoTest {
    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void testFindByUserName() {
        System.out.print(sysUserDao.queryByUserName("admin"));
    }
}
