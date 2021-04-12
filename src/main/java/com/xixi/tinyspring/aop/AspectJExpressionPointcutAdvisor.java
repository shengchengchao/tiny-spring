package com.xixi.tinyspring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class AspectJExpressionPointcutAdvisor  implements  PointcutAdvisor{

    private AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAspectJExpressionPointcut(AspectJExpressionPointcut aspectJExpressionPointcut) {
        this.aspectJExpressionPointcut = aspectJExpressionPointcut;
    }

    public void setAdvisor(Advice advisor) {
        this.advice = advisor;
    }

    @Override
    public Pointcut getPointcut() {
        return aspectJExpressionPointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.aspectJExpressionPointcut.setExpression(expression);
    }

}
