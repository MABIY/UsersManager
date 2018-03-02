package com.lh.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddUserView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<form action='/UsersManager/UserClServlet?type=add' method='post'>");
        out.println("<table border=1px bordercolor=green cellspacing=0 width=500px>");
        out.println("<tr><td>用户名</td><td><input type='text' name='username'/></td></tr>");
        out.println("<tr><td>email</td><td><input type='text' name='email'/></td></tr>");
        out.println("<tr><td>级别</td><td><input type='text' name='grade'/></td></tr>");
        out.println("<tr><td>密码</td><td><input type='text' name='passwd' /></td></tr>");
        out.println("<tr><td><input type='submit' value='添加用户'></td><td><input type='reset' value='重新填写'/></td></tr>");
        out.println("</table>");
        out.println("</form>");
    }
}
