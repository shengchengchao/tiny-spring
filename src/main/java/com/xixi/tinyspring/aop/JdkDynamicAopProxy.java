package com.xixi.tinyspring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 代理工厂
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;


    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
     return    Proxy.newProxyInstance(getClass().getClassLoader(),advisedSupport.getTargetSource().getTargetClass(), this);
    }



    @Override
    public Object invoke(final Object proxy,final Method method,final Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        if(advisedSupport.getMethodMatcher()!=null && advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTarget().getClass())){
           return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));

        }else {
            return  method.invoke(advisedSupport.getTargetSource().getTarget(),args);
        }
    }
}
