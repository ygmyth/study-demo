package com.myth.springboot.async;

import com.myth.springboot.service.Pojo;
import com.myth.springboot.service.SimplePojo;
import com.myth.springboot.service.Testi;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class HelloController implements ApplicationContextAware {

  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  private  static ApplicationContext applicationContext;

  @Autowired
  private Testi hello;
  /**
   * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    HelloController.applicationContext = (applicationContext);
  }

  @GetMapping("/hello")
  public Callable<String> helloController() {
    logger.info(Thread.currentThread().getName() + " 进入helloController方法");
    Callable<String> callable = () -> {
      logger.info(Thread.currentThread().getName() + " 进入call方法");
      String say = hello.sayHello();
      logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
      return say;
    };
    logger.info(Thread.currentThread().getName() + " 从helloController方法返回");
    return callable;
  }




  public static void main(String[] args) {
  }



}  