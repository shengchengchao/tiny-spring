package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public interface BeanPostProcessor {

    /**
     * 暂时没有作用
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 在初始化之后 考虑是否在aop中有匹配 有
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
