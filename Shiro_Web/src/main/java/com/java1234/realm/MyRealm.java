package com.java1234.realm;

import com.java1234.dao.UserDao;
import com.java1234.entity.User;
import com.java1234.util.DbUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/12
 */
public class MyRealm extends AuthorizingRealm {

    private UserDao userDao = new UserDao();
    private DbUtil dbUtil = new DbUtil();

    /**
     * 为当前登录用户授予角色和权限
     * 执行subject.login(token)时调用这个方法
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String  userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Connection con = null;
        try{
            con=dbUtil.getCon();
            authorizationInfo.setRoles(userDao.getRoles(con,userName));
            authorizationInfo.setStringPermissions(userDao.getPermissions(con,userName));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录的用户名

        String userName = (String) token.getPrincipal();
        Connection con = null;
        try {
            con = dbUtil.getCon();
            User user = userDao.getByUserName(con, userName);
            if (user != null) {
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
                return authcInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
