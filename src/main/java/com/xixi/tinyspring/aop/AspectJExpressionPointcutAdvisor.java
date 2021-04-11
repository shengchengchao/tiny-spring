package com.xixi.tinyspring.aop;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class AspectJExpressionPointcutAdvisor  implements  PointcutAdvisor{

    private AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();

    private Advisor advisor;

    public void setAspectJExpressionPointcut(AspectJExpressionPointcut aspectJExpressionPointcut) {
        this.aspectJExpressionPointcut = aspectJExpressionPointcut;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    @Override
    public Pointcut getPointcut() {
        return aspectJExpressionPointcut;
    }

    @Override
    public Advisor getAdvisor() {
        return advisor;
    }
}
