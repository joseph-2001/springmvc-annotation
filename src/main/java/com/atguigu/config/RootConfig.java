package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//Spring的根容器，扫描所有包，但是不扫描Controller
@ComponentScan(value = "com.atguigu",
    excludeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) })
public class RootConfig {

}
