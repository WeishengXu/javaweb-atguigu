package com.atguigu.listeners;

import com.atguigu.ioc.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String path = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new BeanFactory(path);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
