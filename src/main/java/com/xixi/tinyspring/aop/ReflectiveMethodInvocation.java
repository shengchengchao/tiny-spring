package com.xixi.tinyspring.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/** 切点
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class ReflectiveMethodInvocation implements MethodInvocation {


    protected Object target;

    protected Method method;

    protected Object[] args;



    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }


    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target,args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
