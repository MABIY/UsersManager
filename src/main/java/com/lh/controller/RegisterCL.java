package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterCL extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] hobbys = request.getParameterValues("hobby");
        String city = request.getParameter("city");
        out.println("用户名=" + username + "<br/>");
        out.println("密  码=" + password + "<br/>");
        out.println("性  别=" + sex + "<br/>");
        String tem = "你的爱好:";
        if (hobbys != null) {
            for (String hobby : hobbys) {
                tem += hobby;
            }
        }
        tem += "</br>";
        out.println(tem);
        out.println("所在城市:" + city + "</br>");
        out.println("个人介绍:" + request.getParameter("textarea"));
        out.println("隐藏信息:" + request.getParameter("hidden1"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
