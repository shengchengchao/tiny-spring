package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.bean.BeanPostProcessor;
import com.xixi.tinyspring.bean.factory.AbstractFactory;
import com.xixi.tinyspring.bean.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware {


    private AbstractFactory abstractFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.abstractFactory = (AbstractFactory)beanFactory;
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
        if(bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }else if(bean instanceof MethodInterceptor){
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors = abstractFactory.getBeanforType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advise : advisors){
            if(advise.getPointcut().getClassFilter().matches(bean.getClass())){
                AdvisedSupport advisedSupport = new AdvisedSupport();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advise.getAdvice());
                advisedSupport.setMethodMatcher(advise.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                bean = new JdkDynamicAopProxy(advisedSupport).getProxy();
            }

        }
        return bean;
    }
}
