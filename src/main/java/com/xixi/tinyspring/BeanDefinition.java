package com.xixi.tinyspring;

import lombok.Data;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class BeanDefinition {

    private String beanClassName;

    private Object bean;

    private Class beanClass;


    public void setBeanClass(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public Object getBean() {
        return bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public BeanDefinition() {
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
