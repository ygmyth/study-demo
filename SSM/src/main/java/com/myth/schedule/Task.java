package com.myth.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Task {

    //@Scheduled(cron="0 */1 * * * ?")
    public void task1() {
        log.info("============");
        log.info("定时任务。。。");
    }
}
