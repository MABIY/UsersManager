package com.lh.filter;

import com.lh.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyFilter1 extends HttpServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter1...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        if (uri.startsWith("/UsersManager/im") || uri.startsWith("/UsersManager/Login")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("loginuser");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                httpServletRequest.getRequestDispatcher("/LoginServlet").forward(request, response);
            }
        }
    }
}
