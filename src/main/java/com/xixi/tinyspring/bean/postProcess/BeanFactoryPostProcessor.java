package com.xixi.tinyspring.bean.postProcess;

import com.xixi.tinyspring.bean.factory.AbstractFactory;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/8
 */
public interface BeanFactoryPostProcessor {


    /**
     * 在bean 实例话前 修改propertyValue中的值
     * @param beanFactory
     * @throws Exception
     */
    void postProcessBeanFactory(AbstractFactory beanFactory) throws Exception;

}
