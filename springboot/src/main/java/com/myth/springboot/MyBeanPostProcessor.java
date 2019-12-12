package com.myth.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-02-19 14:42
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

  public MyBeanPostProcessor() {
    System.out.println("BeanPostProcessor 构造器实例化");
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if ("person".equals(beanName)) {
      Person person = (Person) bean;
      person.setAge(100);
      System.out.println("BeanPostProcessor postProcessBeforeInitialization对属性进行更改！");
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if ("person".equals(beanName)) {
      Person person = (Person) bean;
      //person.setName("yuangang");
      System.out.println("BeanPostProcessor postProcessAfterInitialization对属性进行更改!");
    }
    return bean;
  }
}
