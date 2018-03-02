package com.lh.view;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CreateCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragram", "no-cache");
        response.setHeader("Content-Type", "image/jpeg");
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 80, 30);
        g.setColor(Color.RED);
        g.setFont(new Font(null, Font.BOLD, 20));
        String num = makeNum();
        request.getSession().setAttribute("checkcode", num);
        g.drawString(num, 10, 25);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    private String makeNum() {
        Random random = new Random();
        StringBuilder num = new StringBuilder((1000+ random.nextInt(8999))+"");
        return num.toString();
    }
}
