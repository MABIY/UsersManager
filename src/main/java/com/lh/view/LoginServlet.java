package com.lh.view;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src='image/01.jpg'/>");
        out.println("<hr/>");
        out.println("<h1>用户登录</h1>");
        out.println("<form action='/UsersManager/LoginClServlet' method='post'>");
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }

            }

        }
        out.println("用户id:<input type='text' name='id' value='" +
                "" + username+
                "'></input><br/>");
        out.println("密 码:<input type='password' name='password' value='" +
                "" +password+
                "'></input><br/>");
        //out.println("验证码:<input type='text' name='checkbox'/><image src='/UsersManager/CreateCode'/></br>");
        out.println("<input type='checkbox' name='iskeepinfo' value='keep'> 在此电脑上保持用户名</input><br/>");
        out.println("<input type='checkbox' name='nokeep' value='nokeep'> delete cookie</input><br/>");
        out.println("<input type='submit' value='登录'/><br/>");
        out.println("</form>");
        String errMessage = (String) request.getAttribute("err");
        if (errMessage != null) {
            out.println("<font color='red'>"+errMessage+"</font>");
        }
        out.println("<hr/>");
        out.println("<img src='image/02.jpg'/>");

    }
}
