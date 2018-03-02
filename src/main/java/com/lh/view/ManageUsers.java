package com.lh.view;

import com.lh.domain.User;
import com.lh.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript' language='javascript'>");
        out.println(" function gotoPageNow() {" +
                "var pageNow=document.getElementById('pageNow');" +
                "window.open('/UsersManager/ManageUsers?pageNow='+pageNow.value,'_self')" +
                "}" +
                "function confirmOper(){" +
                "return window.confirm('真的要删除该数据吗')" +
                "}");
        out.println("</script>");

        out.println("<img src='image/01.jpg'/>欢迎**登 <a href='/UsersManager/MainFrame'>返回主界面</a> <a href='/UsersManager/LoginServlet'>安全退出</a><hr/>");
        out.println("<h1>管理页面</h1>");


        // 分页分组页面连接显示
        int pageNowGroup = 1;

        String pageNowGroupTem = request.getParameter("pageNowGroup");
        if (pageNowGroupTem != null) {
            pageNowGroup = Integer.valueOf(pageNowGroupTem);
        }
        int pageGroupCount = 2;

        int pageNow = 1;
        String pageNowTem = request.getParameter("pageNow");
        if (pageNowTem != null) {
            pageNow = Integer.valueOf(pageNowTem);
        }

        int pageSize = 3;
        int pageCount = 1;
        int rowCount = 1;

        UserService userService = new UserService();

        //3.创建PreparedSatement
        rowCount = userService.getPageCount();
        pageCount = (rowCount - 1) / pageSize + 1;
        out.println("<table border=1 bordercolor=gren cellspacing=0 width=500px>");
        out.println("<tr><th>id</th><th>用户名</th><th>email</th><th>级别</th><th>删除用户</th><th>修改用户</th></tr>");

        ArrayList<User> arrayList = userService.getUsersPage(pageNow, pageSize);
        for (User user : arrayList) {
            out.println("<tr><td>" + user.getId() + "</td><td>" + user.getUsername() + "</td><td>" + user.getEmail() + "</td><td>" + user.getGrade() + "</td>" +
                    "<td><a onclick='return confirmOper();' href='/UsersManager/UserClServlet?type=del&id="+user.getId()+"'>删除用户</a></td>" +
                    "<td><a href='/UsersManager/UserClServlet?type=gotoUpdView&id="+user.getId()+"'>修改用户</a></td>" +
                    "</tr>");
        }
        out.println("</table>");
        out.println("<br/>");


        // 显示分页

        if (pageCount != 0) {

            if (pageGroupCount < pageGroupCount * pageNowGroup) {
                out.println("<a href='/UsersManager/ManageUsers?pageNowGroup=" + (pageNowGroup - 1) + "'><<</a>");
            }
            for (int i = (pageNowGroup - 1) * pageGroupCount + 1; i <= (pageGroupCount * pageNowGroup < rowCount ? pageGroupCount * pageNowGroup : rowCount); i++) {
                out.println("<a href='/UsersManager/ManageUsers?pageNow=" + i + "'><" + i + "></a>");
            }
            if (pageGroupCount * pageNowGroup * pageSize < rowCount) {
                out.println("<a href='/UsersManager/ManageUsers?pageNowGroup=" + (pageNowGroup + 1) + "'>>></a>");
            }
        }
        out.println("&nbsp;&nbsp;&nbsp; 当前页" + pageNow + "/总页数" + pageCount + "<br/>");
        out.println("跳转到 <input type='text' id='pageNow' name='pageNow'/><input onclick='gotoPageNow()'type='button' value='跳'/></br>");


    }
}
