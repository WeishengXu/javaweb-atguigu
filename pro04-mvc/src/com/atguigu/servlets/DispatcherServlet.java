package com.atguigu.servlets;

import com.atguigu.ioc.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
//        beanFactory = new BeanFactory();
        Object beanFactoryObj = getServletContext().getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1, servletPath.lastIndexOf(".do"));
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (operate == null) {
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(operate)) {
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        if (parameterName.equals("request")) {
                            parameterValues[i] = request;
                        } else if (parameterName.equals("response")) {
                            parameterValues[i] = response;
                        } else if (parameterName.equals("session")) {
                            parameterValues[i] = request.getSession();
                        } else {
                            parameterValues[i] = request.getParameter(parameterName);
                            if (parameterValues[i] != null && parameters[i].getType().getName().equals("java.lang.Integer")) {
                                parameterValues[i] = Integer.parseInt((String) parameterValues[i]);
                            }
                        }
                    }

                    method.setAccessible(true);
                    Object methodReturnObj = method.invoke(controllerBeanObj, parameterValues);

                    String methodReturnStr = (String) methodReturnObj;
                    if (methodReturnStr.startsWith("redirect: ")) {
                        String redirectStr = methodReturnStr.substring("redirect: ".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, request, response);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
