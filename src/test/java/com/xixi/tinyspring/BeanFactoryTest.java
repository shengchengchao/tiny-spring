package com.xixi.tinyspring;

import com.xixi.tinyspring.beanFactory.AutoWireCapableBeanFactory;
import com.xixi.tinyspring.beanFactory.BeanFactory;
import org.junit.Test;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class BeanFactoryTest {

    @Test
    public void testBean(){

        BeanFactory beanFactory = new AutoWireCapableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass("com.xixi.tinyspring.HelloWorld");
        beanFactory.registerBean("helloWorld",beanDefinition);

        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.testHello();

    }
}
