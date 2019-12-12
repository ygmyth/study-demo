package com.myth.springboot.interceptor;

import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author liubin
 * @create 2018-01-09 下午7:01
 **/
@Component
public class TimeCalculateHolder {
    static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setTime() {
        threadLocal.set(System.currentTimeMillis());
    }

    public static long getCostTime() {
        return System.currentTimeMillis() - threadLocal.get();
    }
}
