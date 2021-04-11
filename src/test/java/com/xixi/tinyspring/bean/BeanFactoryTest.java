package com.xixi.tinyspring.bean;

import com.xixi.tinyspring.bean.factory.AbstractFactory;
import com.xixi.tinyspring.bean.factory.AutoWireCapableBeanFactory;
import com.xixi.tinyspring.bean.factory.BeanFactory;
import com.xixi.tinyspring.bean.io.ResourceLoader;
import com.xixi.tinyspring.bean.xml.XmlBeanDefinitionReader;
import com.xixi.tinyspring.context.ClassPathXmlApplicationContext;
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
}
