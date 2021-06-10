package com.xixi.tinyspring.bean.aware;

import com.xixi.tinyspring.bean.factory.BeanFactory;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/10
 */
public interface BeanFactoryAware extends Aware {

    /**
     * 加入BeanFactory
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
