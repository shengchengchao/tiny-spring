package com.xixi.tinyspring;

import com.xixi.tinyspring.beanFactory.AbstractFactory;
import com.xixi.tinyspring.beanFactory.AutoWireCapableBeanFactory;
import com.xixi.tinyspring.beanFactory.BeanFactory;
import com.xixi.tinyspring.io.ResourceLoader;
import com.xixi.tinyspring.xml.XmlBeanDefinitionReader;
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
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("ioc.xml");
        BeanFactory beanFactory = new AutoWireCapableBeanFactory();

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBean(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }

        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
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
