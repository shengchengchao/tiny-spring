package com.xixi.tinyspring.beanFactory;

import com.xixi.tinyspring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public interface BeanFactory {


    /**
     * 注册bean
     * @param name
     * @param beanDefinition
     */
    void registerBean(String name, BeanDefinition beanDefinition);

    /**
     * 得到bean
     * @param name
     * @return
     */
    Object getBean(String name);

}
