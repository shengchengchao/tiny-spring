package com.xixi.tinyspring.beanFactory;

import com.xixi.tinyspring.BeanDefinition;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class AutoWireCapableBeanFactory extends AbstractFactory {

    /**
     * 创建bean
     * @param beanDefinition
     */
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        beanDefinition.setBeanClass(beanClassName);
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
            beanDefinition.setBean(bean);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
