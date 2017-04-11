package com.deng.shiro;

import com.deng.entity.SysUser;
import com.deng.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hp on 2017/4/10.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

        SysUser user = sysUserService.queryByUserName(username);

        if (user == null) {
            throw new UnknownAccountException("账号或密码错误");
        }
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("账号或密码错误");
        }
        if (user.getStatus() == 1) {
            throw new LockedAccountException("账号被锁定，请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }
}
