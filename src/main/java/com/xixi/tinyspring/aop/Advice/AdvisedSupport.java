package com.xixi.tinyspring.aop.Advice;

import com.xixi.tinyspring.aop.MethodMatcher;
import com.xixi.tinyspring.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

/** 代理的元数据
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class AdvisedSupport {

    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
