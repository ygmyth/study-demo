package com.myth.springboot.async;

import com.myth.springboot.service.HelloService;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class HelloController {

  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private HelloService hello;

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

  @GetMapping("/world")
  public WebAsyncTask<String> worldController() {
    logger.info(Thread.currentThread().getName() + " 进入helloController方法");

    WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(1000, () -> {
      logger.info(Thread.currentThread().getName() + " 进入call方法");
      String say = hello.sayHello();
      logger.info(Thread.currentThread().getName() + " 从helloService方法返回");
      return say;
    });

    logger.info(Thread.currentThread().getName() + " 从helloController方法返回");

    webAsyncTask.onCompletion(() -> logger.info(Thread.currentThread().getName() + " 执行完毕"));
    webAsyncTask.onTimeout(() -> {
      logger.info(Thread.currentThread().getName() + " onTimeout");
      throw new TimeoutException("调用超时");
    });
    return webAsyncTask;
  }

  @GetMapping("/deferred")
  public DeferredResult<String> executeSlowTask() {
    logger.info(Thread.currentThread().getName() + "进入executeSlowTask方法");
    DeferredResult<String> deferredResult = new DeferredResult<>();
    // 调用长时间执行任务
    hello.sayHello();

    logger.info(Thread.currentThread().getName() + "从executeSlowTask方法返回");
    // 超时的回调方法

    deferredResult.onTimeout(() -> {
      logger.info(Thread.currentThread().getName() + " onTimeout");
      // 返回超时信息
      deferredResult.setErrorResult("time out!");
    });

    // 处理完成的回调方法，无论是超时还是处理成功，都会进入这个回调方法
    deferredResult.onCompletion(
        () -> logger.info(Thread.currentThread().getName() + " onCompletion"));

    return deferredResult;
  }
}  