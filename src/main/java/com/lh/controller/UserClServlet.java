package com.lh.controller;

import com.lh.domain.User;
import com.lh.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        UserService userService = new UserService();
        if (type.equals("del")) {
            String id = request.getParameter("id");
            if (userService.delUser(id)) {
                response.sendRedirect("/UsersManager/Ok");
            } else {
                response.sendRedirect("/UsersManager/Err");
            }
        } else if ("gotoUpdView".equals(type)) {
            String id = request.getParameter("id");
            User user = userService.getUserById(id);
            request.setAttribute("userInfo", user);

            //请求转发
            request.getRequestDispatcher("/UpdateUserView").forward(request, response);
        } else if ("update".equals(type)) {
            //用户数据提交的格式不对
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String email= request.getParameter("email");
            String grade = request.getParameter("grade");
            String passwd = request.getParameter("passwd");
            User user = new User(Integer.valueOf(id), username, email, Integer.valueOf(grade), passwd);
            if (userService.updateUser(user)) {
                response.sendRedirect("/UsersManager/Ok");
            } else {
                response.sendRedirect("/UsersManager/Err");
            }
        } else if ("gotoAddUser".equals(type)) {
            request.getRequestDispatcher("/AddUserView").forward(request,response
            );
        } else if ("add".equals(type)) {

            String username = request.getParameter("username");
            String email= request.getParameter("email");
            String grade = request.getParameter("grade");
            String passwd = request.getParameter("passwd");
            User user = new User();
            user.setPasswd(passwd);
            user.setUsername(username);
            user.setEmail(email);
            user.setGrade(Integer.valueOf(grade));
            if (userService.addUser(user)) {

                response.sendRedirect("/UsersManager/Ok");
            } else {
                response.sendRedirect("/UsersManager/Err");
            }

        }
    }
}
