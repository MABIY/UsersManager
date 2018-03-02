package com.lh.view;

import com.lh.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class MainFrame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("loginuser");
        if (u == null) {
            request.getRequestDispatcher("/LoginServlet").forward(request, response);
            return;
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src='image/01.jpg'/>欢迎**登<a href='/UsersManager/LoginServlet'>返回重新登录</a><hr/>");
        out.println("<h3>请选择您要进行的操作</h3>");
        out.println("<a href='/UsersManager/ManageUsers'>管理用户</a></br>");
        out.println("<a href='/UsersManager/UserClServlet?type=gotoAddUser'>添加用户</a></br>");
        out.println("<a href='/UsersManager/ManageUsers'>查找用户</a></br>");
        out.println("<a href='/UsersManager/ManageUsers'>退出系统</a></br>");
        out.println("<img src='image/02.jpg'/>");
    }
}
