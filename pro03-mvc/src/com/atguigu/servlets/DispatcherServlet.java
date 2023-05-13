package com.atguigu.servlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    Class controllerBeanClass = Class.forName(beanClass);
                    Object beanObj = controllerBeanClass.newInstance();

                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1, servletPath.lastIndexOf(".do"));
        Object controllerBeanObj = beanMap.get(servletPath);

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
