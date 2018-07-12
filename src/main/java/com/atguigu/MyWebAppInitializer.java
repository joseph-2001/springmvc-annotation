package com.atguigu;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.atguigu.config.AppConfig;
import com.atguigu.config.RootConfig;

//WEB容器启动的时候创建对象，调用方法来初始化容器以及前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器的配置类（Spring的配置文件）父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    //获取WEB容器的配置类（SpringMVC配置文件），子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { AppConfig.class };
    }

    //获取DispatcherServlet的映射信息
    // /：拦截所有请求（包括静态资源（*.js、*.png））,但是不包括*.jsp
    // /*：拦截所有的请求，包括*.jsp，JSP页面是Tomcat的JSP引擎负责解析
    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[] { "/" };
    }

}
