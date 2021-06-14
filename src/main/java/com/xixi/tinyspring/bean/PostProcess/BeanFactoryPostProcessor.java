package com.xixi.tinyspring.bean.PostProcess;

import com.xixi.tinyspring.bean.factory.AbstractFactory;
import org.springframework.beans.BeansException;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/8
 */
public interface BeanFactoryPostProcessor {


    /**
     * 在bean 实例话前 修改propertyValue中的值
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(AbstractFactory beanFactory) throws BeansException;

}
