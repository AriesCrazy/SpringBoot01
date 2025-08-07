package com.by.config;

import com.by.servlet.LogFilter;
import com.by.servlet.TestServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    //这种方式等同于在要添加的Servlet类上面加上注解@WebServlet 如@WebServlet("/test")
    //注意项目启动类中要加上注解@ServletComponentScan
    public ServletRegistrationBean testServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new TestServlet());
        bean.addUrlMappings("/test");
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new LogFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    /*@Bean
    public ServletListenerRegistrationBean listenerRegistrationBean() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SessionListener());
        return bean;
    }*/
}


