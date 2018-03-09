package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateSession extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.invalidate();
        out.println(session.getId());
        session.setAttribute("lh", "value");
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(3600);
        //StringBuilder sb = new StringBuilder("JSESSIONID");
        //sb.append("=");
        //sb.append(session.getId());
        //sb.append("; Path=/UsersManager");
        //sb.append("; Max-Age=3600");
        //sb.append("; HttpOnly");
        //response.setHeader("Set-Cookie", sb.toString());
        response.addCookie(cookie);
        out.println("**********");
        out.print(" ");
        out.println(session.getMaxInactiveInterval());
    }
}
