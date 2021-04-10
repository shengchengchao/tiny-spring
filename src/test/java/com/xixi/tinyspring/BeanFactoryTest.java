package com.xixi.tinyspring;

import org.junit.Test;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class BeanFactoryTest {

    @Test
    public void testBean(){

        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorld());
        beanFactory.registerBean("helloWorld",beanDefinition);

        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.testHello();

    }
}
