package com.shtramak.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.shtramak",
        excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {EnableWebMvc.class, Controller.class})})
public class RootConfig {
}
