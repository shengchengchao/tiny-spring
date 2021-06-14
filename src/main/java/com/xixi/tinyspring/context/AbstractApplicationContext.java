package com.xixi.tinyspring.context;


import com.xixi.tinyspring.bean.PostProcess.BeanFactoryPostProcessor;
import com.xixi.tinyspring.bean.PostProcess.BeanPostProcessor;
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
        registerBeanFactoryPostProcessors(abstractBeanFactory);
        registerBeanPostProcessors(abstractBeanFactory);
        onRefresh();
    }

    /**
     * 提前实例化对应的类
     * @param abstractBeanFactory
     */
    protected  void registerBeanFactoryPostProcessors(AbstractFactory abstractBeanFactory) throws Exception {
        List<BeanFactoryPostProcessor> beanForType = abstractBeanFactory.getBeanforType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanForType){
            beanFactoryPostProcessor.postProcessBeanFactory(abstractBeanFactory);
        }
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

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                close();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);

    }

    @Override
    public void close()  {
        abstractBeanFactory.destroySingletons();
    }
}
