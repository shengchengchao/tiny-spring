package com.xixi.tinyspring.beans;

import com.xixi.tinyspring.aop.AdvisedSupport;
import com.xixi.tinyspring.aop.AspectJExpressionPointcut;
import com.xixi.tinyspring.aop.JdkDynamicAopProxy;
import com.xixi.tinyspring.aop.TargetSource;
import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.factory.AbstractFactory;
import com.xixi.tinyspring.bean.factory.AutoWireCapableBeanFactory;
import com.xixi.tinyspring.bean.io.ResourceLoader;
import com.xixi.tinyspring.bean.xml.XmlBeanDefinitionReader;
import com.xixi.tinyspring.context.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class BeanFactoryTest {

    @Test
    public void testBean() throws Exception {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ioc.xml");

        HelloWorld helloWorld = (HelloWorld) classPathXmlApplicationContext.getBean("helloWorld");
        helloWorld.testHello();

    }


    @Test
    public void testPreBean() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("ioc.xml");
        AbstractFactory beanFactory = new AutoWireCapableBeanFactory();

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBean(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
        beanFactory.preInstanceBean();
        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.testHello();

    }
    @Test
    public void aopTest() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ioc.xml");

        HelloWorld helloWorld = (HelloWorld) classPathXmlApplicationContext.getBean("helloWorld");
        helloWorld.testHello();
        //设置代理对象与拦截类
        AdvisedSupport advisedSupport = new AdvisedSupport();
//        advisedSupport.setTargetSource(new TargetSource(HelloWorld.class,helloWorld));
        advisedSupport.setMethodInterceptor(new TimerInterceptor());

        // 获得代理 注意 只能对于接口实现的方法进行代理
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorld proxy = (HelloWorld)jdkDynamicAopProxy.getProxy();
        proxy.testHello();
    }
    @Test
    public void TestClassFilter(){
        String expression = "execution(* cn.javass..IPointcutService+.*())";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldImpl.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void TestMethodMathch() throws NoSuchMethodException {
        String expression = "execution(* com.xixi.tinyspring..*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldImpl.class.getDeclaredMethod("testHello"),HelloWorldImpl.class);
        Assert.assertTrue(matches);
    }
}
