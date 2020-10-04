package com.example.webService.jws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.xml.ws.Endpoint;

/**
 * @Author: xy
 * @Date: 2020/8/30 21:27
 */
@Service
public class BeforeStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final Log log = LogFactory.getLog(this.getClass());

    //@Value("${spring.ws.address}")
    private static final String ADDRESS = "http://localhost:8002/jws/hi";

    @Autowired
    private JwsService jwsService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Endpoint.publish(ADDRESS, jwsService);
        log.info("webService 服务发布成功！！！");
        log.info("wsdl地址：" + ADDRESS+"?wsdl");
    }
}

//@Configuration
//public class BeforeStartup {
//
//    @Autowired
//    private JwsService helloService;
//
//    @Bean
//    public Endpoint endpoint() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(),helloService);
//        endpoint.publish("/helloService");//接口发布在目录下
//        return endpoint;
//    }
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Bean("cxfServletRegistration")
//    public ServletRegistrationBean dispatcherServlet() {
//        return new ServletRegistrationBean(new CXFServlet(), "/cxf/*");
//    }
//
//    @Bean(name = Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus() {
//        return new SpringBus();
//    }
//}