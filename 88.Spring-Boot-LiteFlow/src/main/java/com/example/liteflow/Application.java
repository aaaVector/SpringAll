package com.example.liteflow;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class Application implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  private Map map = new HashMap();
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    map.put(beanName, bean);
    System.out.println(map.size());
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.err.println(map.size());
    return bean;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    System.err.println("============================="+map.size());
  }
}
