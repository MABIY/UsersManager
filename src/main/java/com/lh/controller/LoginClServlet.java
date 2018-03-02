package com.lh.controller;

import com.lh.domain.User;
import com.lh.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginClServlet extends HttpServlet {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users_manager?useSSL=false";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String keep = request.getParameter("iskeepinfo");
        String checkCode = request.getParameter("checkbox");
        String SessionCheckCode = (String) request.getSession().getAttribute("checkcode");
        UserService userService = new UserService();
        if (keep != null && keep.equals("keep")) {
            Cookie cookie = new Cookie("username", id);
            Cookie cookie1 = new Cookie("password", password);
            cookie.setMaxAge(7 * 3600 * 24);
            cookie1.setMaxAge(7 * 3600 * 24);
            response.addCookie(cookie);
            response.addCookie(cookie1);
        }
        User user = new User(Integer.parseInt(id), password);
        //if (userService.checkUser(user) && checkCode.equals(SessionCheckCode)) {
        if (userService.checkUser(user)) {
            // 查询到用户
            request.getRequestDispatcher("/MainFrame").forward(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("loginuser", user);
        } else {
            request.setAttribute("err", "登录信息有误");
            request.getRequestDispatcher("/LoginServlet").forward(request, response);
        }
    }
}
