package org.example.lesson7cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addCookie(new Cookie("id", UUID.randomUUID().toString()));
        UUID check = UUID.randomUUID();
        resp.addCookie(new Cookie("check", check.toString()));

        //    save to DB (id, check)
        //    String from = req.getParameter("from");
        //    resp.sendRedirect(from);

    }
}
