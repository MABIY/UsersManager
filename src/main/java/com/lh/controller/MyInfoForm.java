package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyInfoForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<form action='/UsersManager/RegisterCL' method='post'>");
        out.println("用户名:<input type='text' name='username'/></br>");
        out.println("密  码:<input type='password' name='password'/></br>");
        out.println("<input type='radio' name='sex' value='男'/>男<input type='radio' name='sex' value='女'/>女</br>");
        out.println("<input type='checkbox' name='hobby' value='音乐'/>音乐<input type='checkbox' name='hobby' value='体育'/>体育<input type='checkbox' name='hobby' value='旅游'/>旅游</br>");
        out.println("所在城市:<select name='city'><option value='bj'>北京</option></select></br>");
        out.println("个人介绍:<textarea cols='20' rows='10' name='textarea'>输入介绍:</textarea></br>");
        out.println("<input type='hidden' value='abc' name='hidden1'/>");
        out.println("<input type='submit' value='提交信息'/></br>");
        out.println("</form>");
    }
}
