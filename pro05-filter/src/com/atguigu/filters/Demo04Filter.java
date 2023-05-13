package com.atguigu.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/demo01.do")
@WebFilter("*.do")
public class Demo04Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("helloC");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("helloC2");
    }

    @Override
    public void destroy() {

    }
}
