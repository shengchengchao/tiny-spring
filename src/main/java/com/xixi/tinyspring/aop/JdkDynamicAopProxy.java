package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.aop.Advice.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 代理工厂
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {



    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
     return   Proxy.newProxyInstance(getClass().getClassLoader(),advised.getTargetSource().getInterfaces(), this);
    }



    @Override
    public Object invoke(final Object proxy,final Method method,final Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
        if(advised.getMethodMatcher()!=null && advised.getMethodMatcher().matches(method,advised.getTargetSource().getTarget().getClass())){

            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),method,args));

        }else {
            return  method.invoke(advised.getTargetSource().getTarget(),args);
        }
    }
}
