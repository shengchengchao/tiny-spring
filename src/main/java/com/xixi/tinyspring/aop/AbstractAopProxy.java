package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.aop.Advice.AdvisedSupport;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public  abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
