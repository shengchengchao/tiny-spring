package com.xixi.tinyspring.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public class Cglib2AopProxy extends AbstractAopProxy {


    public Cglib2AopProxy(AdvisedSupport advised) {
        super(advised);
    }



    @Override
    public Object getProxy() {
        Enhancer en = new Enhancer();
        en.setSuperclass(advised.getTargetSource().getTargetClass());
        DynamicAdvisedInterceptor dynamicAdvisedInterceptor = new DynamicAdvisedInterceptor(advised);
        en.setCallback(dynamicAdvisedInterceptor);
        Object proxy = en.create();
        return proxy;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor  {

        private AdvisedSupport advisedSupport;

        private org.aopalliance.intercept.MethodInterceptor methodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
            this.methodInterceptor = advisedSupport.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if (advisedSupport.getMethodMatcher() == null
                    || advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())) {
                return methodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects, methodProxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects, methodProxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.args);
        }
    }
}
