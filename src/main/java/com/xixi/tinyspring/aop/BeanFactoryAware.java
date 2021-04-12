package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.bean.factory.BeanFactory;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
