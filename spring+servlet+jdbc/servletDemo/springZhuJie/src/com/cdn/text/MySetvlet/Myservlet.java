package com.cdn.text.MySetvlet;

import com.cdn.text.JdbcDemo.Jdbc1;
import jdk.internal.dynalink.linker.LinkerServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * desc：
 * author CDN
 * create 2019-06-09 16:02
 * version 1.0.0
 */
public class Myservlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String url = req.getRequestURI();
        String action = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
            switch (action) {
                case "/select":
                    Jdbc1 jdbc1 = new Jdbc1();
                    List list = jdbc1.one();
                    for (Object o : list) {
                        System.out.println(o);
                    }
                    resp.getWriter().println(list);
                    break;
                case "/toMain":   //页面跳转
                    req.getRequestDispatcher("/WEB-INF/main.html").forward(req, resp);
                    break;
                default:
                    break;
            }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化");


        super.init();
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
