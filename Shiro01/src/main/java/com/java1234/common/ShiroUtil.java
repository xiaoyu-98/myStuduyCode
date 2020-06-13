package com.java1234.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/10
 */
public class ShiroUtil {
    public static Subject login(String configFile, String userName, String password) {
        //读取配置文件，初始化SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        //绑定到SecuritUtils上
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前正在执行的用户
        Subject currentUser = SecurityUtils.getSubject();
        //创建token令牌，用户名/密码
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        //身份认证
        try {
            currentUser.login(token);
            System.out.println("身份认证成功！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("身份认证失败！");
        }
        return currentUser;

    }
}
