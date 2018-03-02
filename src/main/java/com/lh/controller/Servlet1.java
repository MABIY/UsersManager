package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        boolean hasLatTime = false;
        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTime")) {
                    out.println("上次登录时间:" + URLDecoder.decode(cookie.getValue(), "utf-8"));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cookie.setValue(URLEncoder.encode(simpleDateFormat.format(new Date()),"utf-8"));
                    cookie.setMaxAge(7 * 3600 * 24);
                    response.addCookie(cookie);
                    hasLatTime = true;
                    break;
                }
            }
        }
        if (!hasLatTime) {
            out.println("欢迎第一次登录");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Cookie cookie = new Cookie("lastTime", URLEncoder.encode(simpleDateFormat.format(new Date()),"utf-8"));
            cookie.setMaxAge(7*3600*24);
            response.addCookie(cookie);
        }
    }
}
