package com.java1234.shiro;

import com.java1234.common.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/10
 */
public class RoleTest {
    /**
     * 判断是否有相应角色
     */
    @Test
    public void testHasRole() {
        Subject currenUser = ShiroUtil.login("classpath:shiro_role.ini", "jack", "123");
        //单个测试
        System.out.println(currenUser.hasRole("role2")?"有这个角色":"无这个角色");
        //多个测试
        boolean[] results = currenUser.hasRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println(results[0]?"有role1角色":"无role1角色");
        System.out.println(results[1]?"有role2角色":"无role2角色");
        System.out.println(results[2]?"有role3角色":"无role3角色");
        //同时有角色
        System.out.println(currenUser.hasAllRoles(Arrays.asList("role1","role2"))?"role1,role2都有":"不全有");

        //check方法,如果没有这个角色会报错 ===相当于一个检查
        currenUser.checkRole("role1");
    }
}
