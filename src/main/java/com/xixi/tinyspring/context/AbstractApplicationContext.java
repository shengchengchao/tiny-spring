package com.xixi.tinyspring.context;


import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.postProcess.ApplicationContextAwarePostProcess;
import com.xixi.tinyspring.bean.postProcess.BeanFactoryPostProcessor;
import com.xixi.tinyspring.bean.postProcess.BeanPostProcessor;
import com.xixi.tinyspring.bean.event.*;
import com.xixi.tinyspring.bean.factory.AbstractFactory;

import java.util.List;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public abstract class AbstractApplicationContext implements ApplicationContext  {

    protected AbstractFactory abstractBeanFactory;
    protected ApplicationEventMulticaster applicationEventMulticaster;
    public AbstractApplicationContext(AbstractFactory abstractBeanFactory) {
        this.abstractBeanFactory = abstractBeanFactory;
    }

    public void refresh() throws Exception{
        loadBeanDefinitions(abstractBeanFactory);

        registerBeanFactoryPostProcessors(abstractBeanFactory);
        registerBeanPostProcessors(abstractBeanFactory);
        abstractBeanFactory.addBeanPostProcessor(new ApplicationContextAwarePostProcess(this));
        //事件发布者
        registerApplicationEvent(abstractBeanFactory);
         //事件监听器
        registerApplicationEventListener(abstractBeanFactory);
        onRefresh();
        finishRefresh();
    }


    /**
     * 发布容器刷新完成事件
     */
    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }


    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    protected  void registerApplicationEventListener(AbstractFactory abstractBeanFactory) throws Exception {
        List<ApplicationEventListener> list = abstractBeanFactory.getBeanforType(ApplicationEventListener.class);
        for(ApplicationEventListener applicationEventListener : list){
            applicationEventMulticaster.addEvent(applicationEventListener);
        }
    }

    protected  void registerApplicationEvent(AbstractFactory abstractBeanFactory){
        AbstractApplicationEventMulticaster abstractApplicationEventMulticaster = new AbstractApplicationEventMulticaster();
        BeanDefinition beanDefinition = new BeanDefinition();
        this.applicationEventMulticaster = abstractApplicationEventMulticaster;
        beanDefinition.setScope(BeanDefinition.SINGLETON);
        beanDefinition.setBean(abstractApplicationEventMulticaster);
        beanDefinition.setBeanClass(AbstractApplicationEventMulticaster.APPLICATION_EVENT_MULTICASTER_BEAN_NAME);
        abstractBeanFactory.registerBean(AbstractApplicationEventMulticaster.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,beanDefinition);
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
        List<BeanPostProcessor> beanForType = abstractBeanFactory.getBeanforType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanForType){
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
