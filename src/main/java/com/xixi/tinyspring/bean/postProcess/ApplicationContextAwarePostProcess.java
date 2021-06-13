package com.xixi.tinyspring.bean.postProcess;

import com.xixi.tinyspring.bean.aware.ApplicationContextAware;
import com.xixi.tinyspring.context.ApplicationContext;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/10
 */
public class ApplicationContextAwarePostProcess implements BeanPostProcessor {

    private ApplicationContext applicationContext;

    public ApplicationContextAwarePostProcess(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 暂时没有作用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    /**
     * 在初始化之后 考虑是否在aop中有匹配 有
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
