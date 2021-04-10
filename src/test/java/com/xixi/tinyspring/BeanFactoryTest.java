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
    public void testBean() throws Exception {

        BeanFactory beanFactory = new AutoWireCapableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass("com.xixi.tinyspring.HelloWorld");

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","helloWorld"));
        beanDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBean("helloWorld",beanDefinition);

        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.testHello();

    }
}
