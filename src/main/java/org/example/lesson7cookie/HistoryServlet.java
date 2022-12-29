package org.example.lesson7cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

public class HistoryServlet extends HttpServlet {

    private final CalcHistory log;

    public HistoryServlet(CalcHistory log) {
        this.log = log;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cs = req.getCookies();   //null !!!
        //  String here = req.getContextPath() + req.getRequestURI() + req.getQueryString()
        Optional.ofNullable(cs)
                .flatMap(cc ->
                        Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst()
                ).ifPresentOrElse(
                        c -> {
                            String userId = c.getValue();
                            try (PrintWriter w = resp.getWriter()) {
                                log.getAll(userId).forEach(s -> w.println(s));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {
                            System.out.println("HistoryServlet.no cookie");
                            try {
                                resp.sendRedirect("/login"); // "/login" + "?from=" + here
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }
}
