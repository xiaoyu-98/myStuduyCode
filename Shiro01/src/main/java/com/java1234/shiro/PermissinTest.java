package com.java1234.shiro;

import com.java1234.common.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/10
 */
public class PermissinTest {

    /**
     * 权限测试
     */
    @Test
    public void testIsPermitted() {
//        Subject currenUser = ShiroUtil.login("classpath:shiro_permission.ini", "java1234", "123456");
        Subject currenUser = ShiroUtil.login("classpath:shiro_permission.ini", "jack", "123");
        //验证权限
        System.out.println(currenUser.isPermitted("user:select")?"有user:select权限":"无user:select权限");
        //多个验证
        boolean[] results = currenUser.isPermitted("user:select", "user:update", "user:delete");
        System.out.println(results[0]?"有user:select":"无user:select");
        System.out.println(results[1]?"有user:update":"无user:update");
        System.out.println(results[2]?"有user:delete":"无user:delete");
        //所有权限满足
        System.out.println(currenUser.isPermittedAll("user:select", "user:update")?"两个权限都有":"不全有");
        //没有这个权限会出错
        currenUser.checkPermission("user:update");

    }
}
