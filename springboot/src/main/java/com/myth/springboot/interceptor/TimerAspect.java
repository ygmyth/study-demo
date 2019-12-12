package com.myth.springboot.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author yuan gang
 * @Description  方法级计时器
 * @Date  2018/11/12
 **/
@Aspect
@Component
@Slf4j
public class TimerAspect {

    @Pointcut("@annotation(com.myth.springboot.interceptor.Timer)")
        public void timer() {
    }

    @Before("timer()")
    public void before(JoinPoint joinPoint) {
        log.info("Time Before:");
        TimeCalculateHolder.setTime();
        String strMethodName = joinPoint.getSignature().getName();
        String strClassName = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        StringBuilder bfParams = new StringBuilder();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (params != null && params.length > 0) {
            Enumeration<String> paraNames = request.getParameterNames();
            String key;
            String value;
            while (paraNames.hasMoreElements()) {
                key = paraNames.nextElement();
                value = request.getParameter(key);
                bfParams.append(key).append("=").append(value).append("&");
            }
            if (StringUtils.isEmpty(bfParams)) {
                bfParams.append(request.getQueryString());
            }
        }
        String parameters = bfParams.toString();
        String strMessage = String
                .format("\n>>[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, parameters);
        log.info(strMessage);
    }

    @After("timer()")
    public void after() {
        log.info("Timer After，耗时（毫秒） : {}", TimeCalculateHolder.getCostTime());
    }
}