package com.myth.springboot;

import java.beans.PropertyDescriptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-02-19 14:43
 **/
@Component
public class MyInstantiationAwareBeanPostProcessor extends
    InstantiationAwareBeanPostProcessorAdapter {

  public MyInstantiationAwareBeanPostProcessor() {
    super();
    System.out.println("InstantiationAwareBeanPostProcessor 构造器实例化");
  }

  // 接口方法、实例化Bean之前调用
  @Override
  public Object postProcessBeforeInstantiation(Class beanClass,
      String beanName) throws BeansException {
    if ("person".equals(beanName)) {
      System.out.println("InstantiationAwareBeanPostProcessor postProcessBeforeInstantiation方法");
    }
    return null;
  }

  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    if ("person".equals(beanName)) {
      Person person = (Person) bean;
      person.setName("world");

      System.out.println("InstantiationAwareBeanPostProcessor postProcessAfterInstantiation方法 set name = world");
    }
    return super.postProcessAfterInstantiation(bean, beanName);
  }

  // 接口方法、实例化Bean之后调用
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
    if ("person".equals(beanName)) {
      System.out.println("InstantiationAwareBeanPostProcessor postProcessAfterInitialization方法");
    }
    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if ("person".equals(beanName)) {

      System.out
          .println("InstantiationAwareBeanPostProcessor postProcessBeforeInitialization方法");
    }
    return super.postProcessBeforeInitialization(bean, beanName);
  }

  // 接口方法、设置某个属性时调用
  @Override
  public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds,
      Object bean, String beanName)
      throws BeansException {
    if ("person".equals(beanName)) {
      System.out.println("InstantiationAwareBeanPostProcessor postProcessPropertyValues方法");
    }
    return pvs;
  }


}
