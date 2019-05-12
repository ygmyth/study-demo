package com.myth.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TraceMethodArgsAndReturn {
    @OnMethod(clazz="org.myth.btrace.BTraceTest",method="add",location=@Location(Kind.RETURN))
    public static void func(int a,int b,@Return int result){
        println("打印堆栈:");
        jstack();
        println(strcat("方法参数A：",str(a)));
        println(strcat("方法参数B：",str(b)));
        println(strcat("方法返回C：",str(result)));
    }
}