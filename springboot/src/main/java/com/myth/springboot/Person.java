package com.myth.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-02-19 14:38
 **/
@Component
public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
    InitializingBean, DisposableBean {

  private String name;
  private int age;
  private int phone;
  private BeanFactory beanFactory;
  private String beanName;
  private ApplicationContext applicationContext;

  public Person() {
    System.out.println("Person 构造器初始化");
  }

  @Override
  public String toString() {
    return "myth.myth.entity.Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", phone=" + phone +
        '}';
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    System.out.println("Person 注入属性 注入属性age");
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    System.out.println("Person 注入属性 注入属性name");
    this.name = name;
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    System.out.println("Person 注入属性 注入属性phone");
    this.phone = phone;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    System.out.println("BeanFactoryAware setBeanFactory()");
    this.beanFactory = beanFactory;
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("BeanNameAware setBeanName()");
    this.beanName = name;
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("DiposibleBean DiposibleBean.destory()");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean InitializingBean.afterPropertiesSet()");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    System.out.println("ApplicationContext 调用ApplicationContextAware.setApplicationContext()");
    this.applicationContext = applicationContext;
  }

  public void myInit() {
    System.out.println("init-method 调用<bean>的init-method属性指定的初始化方法");
  }

  public void myDestory() {
    System.out.println("destroy-method 调用<bean>的destroy-method属性指定的初始化方法");
  }

}
