package com.xixi.tinyspring.beanFactory;

import com.xixi.tinyspring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public abstract class AbstractFactory implements BeanFactory {

    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name) {
        //这里可以做成懒加载来完成 就是在考虑没有这个beanDefinition 再去创建beanDefinition
        //这需要考虑 两种情况 beanDefinition 没有 和 其中的bean没有
        Object bean = beanDefinitionMap.get(name).getBean();
        return bean;
    }


    @Override
    public void registerBean(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreateBean(beanDefinition);
        beanDefinitionMap.put(name,beanDefinition);
    }

    /**
     * 创建bean
     * @param beanDefinition
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
