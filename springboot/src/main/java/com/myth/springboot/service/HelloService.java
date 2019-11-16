package com.myth.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-11-15
 **/
@Service
@Slf4j
public class HelloService {
  public String sayHello() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      log.info("sleep 超时 ");
    }
    return "say hello!";
  }
}
