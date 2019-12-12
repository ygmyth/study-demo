package com.myth.springboot.async;

import com.myth.springboot.interceptor.Timer;
import com.myth.springboot.service.Testi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-11-15
 **/
@Service
@Slf4j
public class HelloService implements Testi {

  @Override
  public String sayHello() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    return "say hello!";
  }
}
