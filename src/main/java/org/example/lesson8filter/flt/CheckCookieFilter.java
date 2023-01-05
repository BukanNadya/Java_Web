package org.example.lesson8filter.flt;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CheckCookieFilter implements HTTPFilter {
    @Override
    public void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cs = request.getCookies();
        Optional<Cookie> c = Optional.ofNullable(cs).flatMap(cc -> Arrays.stream(cc).filter(c1 -> c1.getName().equals("id")).findFirst());
        if (c.isPresent()) filterChain.doFilter(request, response);
        else
            response.sendRedirect("/login");
    }
}
