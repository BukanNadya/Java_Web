package org.example.lesson6;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HistoryServlet extends HttpServlet {

    private final CalcHistory log;

    public HistoryServlet(CalcHistory log) {
        this.log = log;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try (PrintWriter w = resp.getWriter()) {
           log.getAll().forEach(s -> w.println(s));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
