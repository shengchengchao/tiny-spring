package com.xixi.tinyspring.aop.Advice;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/13
 */
public interface MethodBeforeAdvice extends Advisor {
    /**
     * 前置通知
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;


}
