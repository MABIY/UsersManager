package com.lh.view;

import com.lh.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateUserView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        User user = (User) request.getAttribute("userInfo");
        out.println("<form action='/UsersManager/UserClServlet?type=update' method='post'>");
        out.println("<table border=1px bordercolor=green cellspacing=0 width=500px>");
        out.println("<tr><td>id</td><td><input type='text' readonly name='id' value='"+user.getId()+"'></td></tr>");
        out.println("<tr><td>用户名</td><td><input type='text' name='username' value='"+user.getUsername()+"'/></td></tr>");
        out.println("<tr><td>email</td><td><input type='text' name='email' value='"+user.getEmail()+"'/></td></tr>");
        out.println("<tr><td>级别</td><td><input type='text' name='grade' value='"+user.getGrade()+"'/></td></tr>");
        out.println("<tr><td>密码</td><td><input type='text' name='passwd' value='"+user.getPasswd()+"'/></td></tr>");
        out.println("<tr><td><input type='submit' value='修改用户'></td><td><input type='reset' value='重新填写'/></td></tr>");
        out.println("</table>");
        out.println("</form>");
    }
}
