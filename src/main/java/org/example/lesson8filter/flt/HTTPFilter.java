package org.example.lesson8filter.flt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HTTPFilter extends Filter {

    @Override
    default void init(FilterConfig filterConfig) {}

    default boolean isHTTP(ServletRequest request0, ServletResponse response0) {
        return request0 instanceof HttpServletRequest && response0 instanceof HttpServletResponse;
    }

    void doHTTPFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    @Override
    default void doFilter(ServletRequest request0, ServletResponse response0, FilterChain filterChain) throws IOException, ServletException {
        if(isHTTP(request0, response0)) {
            HttpServletRequest request = (HttpServletRequest) request0;
            HttpServletResponse response = (HttpServletResponse) response0;
            doHTTPFilter(request, response, filterChain);
        } else filterChain.doFilter(request0, response0);
    }

    @Override
    default void destroy() {}
}
