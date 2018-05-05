package cn.com.lyf.wechat.realms;


import cn.com.lyf.wechat.dao.UserDao;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import cn.com.lyf.wechat.entity.User;

import java.util.*;


/**
 * 获取用户的角色和权限信息
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    
    /**
     * 用于获取登录成功后的角色、权限等信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getRoleId());
        Set<String> set = new HashSet<>();
        info.setStringPermissions(set);
        return  info;
    }

    /**
     * 验证当前登录的Subject
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	//把AuthenticationToken转换成UsernamePasswordLoginTypeToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = (String) upToken.getUsername();
        String password = new String((char[]) upToken.getPassword());
        User user = userDao.selectUserByName(username, password);
        if (user==null){
            throw new UnknownAccountException("账号密码错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        return info;
    }
    
      
}