package com.lh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //TODO 下载样例
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=winter.png");
        String path = this.getServletContext().getRealPath("/image/ctrl+b.png");
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
        byte[] buff = new byte[1024];
        int len = 0;
        OutputStream os = response.getOutputStream();
        while ((len = bufferedInputStream.read(buff)) > 0) {
            os.write(buff, 0, len);
        }
        os.close();
        bufferedInputStream.close();
    }
}
