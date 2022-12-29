package org.example.lesson8filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckCookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request0, ServletResponse response0, FilterChain filterChain) throws IOException, ServletException {

        if(request0 instanceof HttpServletRequest && response0 instanceof ServletResponse) {
            HttpServletRequest request = (HttpServletRequest) request0;
            HttpServletResponse response = (HttpServletResponse) response0;
        }
    }

    @Override
    public void destroy() {}
}
