package com.xixi.tinyspring.context;


import com.xixi.tinyspring.bean.factory.AbstractFactory;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public abstract class AbstractApplicationContext implements ApplicationContext  {

    protected AbstractFactory abstractBeanFactory;

    public AbstractApplicationContext(AbstractFactory abstractBeanFactory) {
        this.abstractBeanFactory = abstractBeanFactory;
    }

    public void refresh() throws Exception{

    }

    @Override
    public Object getBean(String name) throws Exception {
        Object bean = abstractBeanFactory.getBean(name);
        return bean;
    }

}
