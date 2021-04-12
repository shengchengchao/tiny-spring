package com.xixi.tinyspring.bean.factory;

import com.xixi.tinyspring.aop.AspectJExpressionPointcutAdvisor;
import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public abstract class AbstractFactory implements BeanFactory {

    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    List<String> beanNameList = new ArrayList<String>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    @Override
    public Object getBean(String name) throws Exception {
        //这里可以做成懒加载来完成 就是在考虑没有这个beanDefinition 再去创建beanDefinition
        //这需要考虑 两种情况 beanDefinition 没有 和 其中的bean没有
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition ==null ){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if( bean ==null){
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(name, bean);
            beanDefinition.setBean(bean);

        }
        return bean;
    }

    protected Object initializeBean(String beanName,Object bean) throws Exception{
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean= beanPostProcessor.postProcessBeforeInitialization(bean,beanName);
        }


        for (BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean= beanPostProcessor.postProcessAfterInitialization(bean,beanName);
        }
        return bean;
    }

    public void registerBean(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name,beanDefinition);
        beanNameList.add(name);
    }

    /**
     * 创建bean
     * @param beanDefinition
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;


    public void preInstanceBean() throws Exception {
        for (String name : beanNameList) {
            getBean(name);
        }
    }

    /**
     * 得到 beanPostProcessorClass 的bean  该类会被优先初始化
     * @param type
     * @throws Exception
     */
    public  List getBeanforType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for (String beanName : beanNameList){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            boolean flag = type.isAssignableFrom(beanDefinition.getBeanClass());
            if(flag){
                beans.add(getBean(beanName));
            }
        }
        return beans;
    }

    public  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
