package com.xixi.tinyspring.context;


import com.xixi.tinyspring.bean.BeanPostProcessor;
import com.xixi.tinyspring.bean.factory.AbstractFactory;

import java.util.List;

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
        loadBeanDefinitions(abstractBeanFactory);
        registerBeanPostProcessors(abstractBeanFactory);
        onRefresh();
    }

    protected  void onRefresh() throws Exception {
        abstractBeanFactory.preInstanceBean();
    }

    protected  void registerBeanPostProcessors(AbstractFactory abstractBeanFactory) throws Exception {
        List<BeanPostProcessor> beanforType = abstractBeanFactory.getBeanforType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanforType){
            abstractBeanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        Object bean = abstractBeanFactory.getBean(name);
        return bean;
    }


    protected abstract void loadBeanDefinitions(AbstractFactory beanFactory) throws Exception ;
}
