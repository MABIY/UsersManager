package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie cookie = new Cookie("tt", "lh");

        Cookie cookie1 = new Cookie("ji", "lh");
        cookie.getValue();
        response.addCookie(cookie);
        response.addCookie(cookie1);
        out.println(this.getClass().getSimpleName());
    }
}
