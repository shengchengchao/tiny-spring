package com.xixi.tinyspring.beans;



import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class TimerInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long time = System.nanoTime();
        System.out.println("Invocation of Method " + methodInvocation.getMethod().getName() + " start!");
        Object proceed = methodInvocation.proceed();
        System.out.println("Invocation of Method " + methodInvocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
                + " nanoseconds.");
        return proceed;
    }
}
