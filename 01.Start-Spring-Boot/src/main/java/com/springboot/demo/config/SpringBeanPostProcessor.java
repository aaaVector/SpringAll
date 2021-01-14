package com.springboot.demo.config;

import java.beans.PropertyDescriptor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 17:52
 */
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor, InstantiationAwareBeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    final Class<?> targetClass = AopUtils.getTargetClass(o);
    System.out.println("targetClass: " + targetClass.getName());
    return o;
  }

  @Override
  public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
    return o;
  }

  @Override
  public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
    return null;
  }

  @Override
  public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
    return false;
  }

  @Override
  public PropertyValues postProcessPropertyValues(PropertyValues propertyValues,
      PropertyDescriptor[] propertyDescriptors, Object o, String s) throws BeansException {
    return null;
  }
}
