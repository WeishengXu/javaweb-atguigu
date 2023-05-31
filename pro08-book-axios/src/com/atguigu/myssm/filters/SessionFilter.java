package com.atguigu.myssm.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(urlPatterns = {"*.do", "*.html"},
//        initParams = @WebInitParam(
//                name = "whitelist",
//                value = "/pro07/page.do?operate=page&page=user/login,/pro07/user.do?null,/pro07/page.do?operate=page&page=user/regist"))
public class SessionFilter implements Filter {
    private List<String> whitelist;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String whitelistStr = filterConfig.getInitParameter("whitelist");
        whitelist = Arrays.asList(whitelistStr.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String url = request.getRequestURI() + "?" + request.getQueryString();
        Object userObj = session.getAttribute("currentUser");
        if (whitelist.contains(url) || userObj != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("page.do?operate=page&page=user/login");
        }
    }

    @Override
    public void destroy() {

    }
}
