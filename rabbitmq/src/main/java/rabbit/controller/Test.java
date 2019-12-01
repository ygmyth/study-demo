package rabbit.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rabbit.one.MQTest;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-11-22
 **/
@RestController
@Slf4j
@RequestMapping("/mq")
public class Test {

  @Resource
  private MQTest mqTest;

  @GetMapping("/direct")
  public String direct(@RequestParam(required = false) String key) {
    mqTest.sendDirect();
    return "success";
  }

  @GetMapping("/fanout")
  public String fanout(@RequestParam(required = false) String key) {
    mqTest.sendFanout();
    return "success";
  }

  @GetMapping("/topic")
  public String topic(@RequestParam(required = false) String key) {
    mqTest.sendTopic();
    return "success";
  }

}
