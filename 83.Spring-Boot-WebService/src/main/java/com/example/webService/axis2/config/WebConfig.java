package com.example.webService.axis2.config;

import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Axis注入bean配置
 *
 * @Author: xy
 * @Date: 2020/9/4 22:56
 */
public class WebConfig {

    /**
     * 配置servlet的Bean，添加axis2
     */
    @Bean
    public ServletRegistrationBean<AxisServlet> servletRegistrationBean() {
        ServletRegistrationBean<AxisServlet> servletRegistrationBean = new ServletRegistrationBean<>(new AxisServlet(), "/services/*");
        servletRegistrationBean.addInitParameter("axis2.repository.path", this.getClass().getResource("/WEB-INF").getPath().toString());
        return servletRegistrationBean;
    }

}
