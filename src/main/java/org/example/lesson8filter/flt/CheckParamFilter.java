package org.example.lesson8filter.flt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckParamFilter implements HTTPFilter {
    @Override
    public void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            Integer.parseInt(request.getParameter("x"));
            Integer.parseInt(request.getParameter("y"));
            filterChain.doFilter(request, response);
        } catch (Exception x) {
            try(PrintWriter w = response.getWriter()) {
                w.println("x & y are expected to be int");
            }
            response.setStatus(400);
        }
    }
}
