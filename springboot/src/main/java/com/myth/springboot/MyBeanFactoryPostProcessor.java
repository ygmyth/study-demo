package com.myth.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-02-19 14:43
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  public MyBeanFactoryPostProcessor() {
    System.out.println("BeanFactoryPostProcessor 构造器实例化");
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    BeanDefinition db = beanFactory.getBeanDefinition("person");
    db.getPropertyValues().addPropertyValue("phone", "110");
    System.out.println("BeanFactoryPostProcessor postProcessBeanFactory 方法");
  }
}
