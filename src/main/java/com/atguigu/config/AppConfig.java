package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.atguigu.interceptor.MyFirstInterceptor;

//SpringMVC子容器，扫描所有包，但是只扫描Controller
//useDefaultFilters = false，禁用默认的过滤规则
@ComponentScan(value = "com.atguigu",
    includeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) },
    useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    //定制视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认页面都从/WEB-INF/xxx.jsp
        //registry.jsp();
        //或者自定义路径
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    //定制静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //相当于  <mvc:default-servlet-handler />
        configurer.enable();
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyFirstInterceptor());
        //设定拦截器要拦截的路径
        interceptorRegistration.addPathPatterns("/**");
    }
}
