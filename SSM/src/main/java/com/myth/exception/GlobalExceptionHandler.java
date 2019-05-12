package com.myth.exception;

import com.myth.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*@ControllerAdvice
@ResponseBody*/
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception e) {
        log.info("",e);
        return Response.error(1, "业务异常");
    }

    @ExceptionHandler(value = GlobalException.class)
    public Response globalExceptionHandler(HttpServletRequest request, Exception e) {
        GlobalException ex = (GlobalException)e;
        return ex.getResponse();
    }
}
