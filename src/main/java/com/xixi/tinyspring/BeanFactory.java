package com.xixi.tinyspring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class BeanFactory {

    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * 注册bean
     * @param name
     * @param beanDefinition
     */
    public void registerBean(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }

    /**
     * 得到bean
     * @param name
     * @return
     */
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

}
