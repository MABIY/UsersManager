package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyImportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String refer = request.getHeader("Referer");

        if (refer == null || !refer.startsWith("http://localhost:8080")) {
            out.println("小子 想盗链吗");
            return;
        }
        out.println("姓名: lh<br/>");
        out.println("性别: man<br/>");
        out.println("工作: jobless<br/>");
    }
}
