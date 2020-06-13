package com.java1234.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/11
 */

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login doget");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login dopost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"====" + password);
        //调用
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //调用这个的时候会调用自定义的realm
            subject.login(token);
            //可以获取session（通过shiro）
            Session session = subject.getSession();
            System.out.println(session.getId());
            System.out.println(session.getHost());
            System.out.println(session.getTimeout());
            HttpSession session1 = req.getSession();
            System.out.println("============");
            System.out.println(session1.getId());
            resp.sendRedirect("success.jsp");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            req.setAttribute("errorInfo","用户名或密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
